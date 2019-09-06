import json
import os

import numpy as np
import pandas as pd
import sys
from aggregator import GlobalStateAggregator
from env import Agent, Environment
from math import floor
from tensorflow.python.keras.models import load_model


def prepare_environment(config_address, model_from_file=None):
    with open(config_address, 'r') as sim_file:
        jstring = sim_file.read()
    config = json.loads(jstring)

    def _agent_generator(name):
        model = load_model(model_from_file + name + '.h5') if model_from_file is not None else None
        return Agent(config[name]['constants'], config[name]['states'], name, config[name]['hyper_parameters'], model)

    agents_dict = {agent_name: _agent_generator(agent_name) for agent_name in config}

    env = Environment()
    env.register_agents(*(agents_dict.values()))
    for name in agents_dict:
        to_agents = [agents_dict[to_name] for to_name in config[name]['to_agents']]
        env.register_connections(agents_dict[name], *to_agents)

    env.compile()
    return env, agents_dict


def prepare_data(data_address, with_constants):
    data = {}
    for agent in data_address:
        raw_data = pd.read_csv(data_address[agent])
        constants = raw_data.filter(like='const')
        states = raw_data.filter(like='var')
        if with_constants:
            data[agent] = {'constants': constants, 'states': states}
        else:
            data[agent] = states
    return data


def train_test_split(data_input, data_output, train_ratio):
    dataset_size = len(list(data_output.values())[0])
    train_index = np.random.choice(range(dataset_size), floor(train_ratio * dataset_size))

    train_input = {agent: {
        'constants': data_input[agent]['constants'].loc[train_index],
        'states': data_input[agent]['states'].loc[train_index]
    } for agent in data_input}
    test_input = {agent: {
        'constants': data_input[agent]['constants'].drop(train_index),
        'states': data_input[agent]['states'].drop(train_index)
    } for agent in data_input}

    train_output = {agent: data_output[agent].loc[train_index] for agent in data_output}
    test_output = {agent: data_output[agent].drop(train_index) for agent in data_output}

    return train_input, train_output, test_input, test_output


def setup(config_address, model_from_file=None):
    env, agent_dict = prepare_environment(config_address, model_from_file)
    agents = agent_dict.values()
    input_address = {agent: f'target/data/{agent.name}_x.csv' for agent in agents}
    output_address = {agent: f'target/data/{agent.name}_y.csv' for agent in agents}
    data_input = prepare_data(input_address, True)
    data_output = prepare_data(output_address, False)
    return env, agent_dict, data_input, data_output

def get_aggregator(input_data):
    population = [input_data[agent]["constants"]["const_number"] for agent in input_data if agent.name == "Person"][0]
    return GlobalStateAggregator(population, ["capital", "total_value_destroyed", "unemploymentRate",
                                                                       "happiness", "valueProduced", "goodwill"])

def learn_input(data_input, data_output):
    input_scales = {agent: {type: (data_input[agent][type].mean(), data_input[agent][type].std())
                            for type in data_input[agent]}
                    for agent in data_input}
    return env.learn_input(data_output, get_aggregator(data_input), input_scales, epochs=10 ** 3)



if __name__ == '__main__':
    os.chdir('../../..')  # going to the root of the project

    if len(sys.argv) < 2:
        raise Exception("action required")

    action = sys.argv[1]
    if action == 'train':
        env, agent_dict, data_input, data_output = setup('simulation.json')
        agents = agent_dict.values()
        train_input, train_output, test_input, test_output = train_test_split(data_input, data_output, 0.75)
        env.solo_train(train_input, train_output, training_hyper_params={
            agent: {'epochs': 200} for agent in agents
        })
        env.solo_test(test_input, test_output)

        env.group_train(train_input, train_output, get_aggregator(train_input))
        print("group test:", env.group_test(test_input, test_output, get_aggregator(test_input)))
        print("learn input:", learn_input(data_input, data_output))

        if len(sys.argv) >= 3 and sys.argv[2] == "--save":
            env.save_models("target/models/")

    elif action == 'evaluate':
        env, agent_dict, data_input, data_output = setup('simulation.json', 'target/models/')
        env.solo_test(data_input, data_output)

        print("group test:", env.group_test(data_input, data_output, get_aggregator(data_input)))
        print("learn input:", learn_input(data_input, data_output))
