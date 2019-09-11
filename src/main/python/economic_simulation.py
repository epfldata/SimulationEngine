import json
import os

import numpy as np
import pandas as pd
import sys
from aggregator import GlobalStateAggregator
from env import Agent, Environment
from math import floor


def prepare_environment(config_address, models_address=None):
    with open(config_address, 'r') as sim_file:
        jstring = sim_file.read()
    config = json.loads(jstring)

    def _agent_generator(name):
        return Agent(config[name]['constants'], config[name]['states'], name, config[name]['hyper_parameters'])

    agents_dict = {agent_name: _agent_generator(agent_name) for agent_name in config}

    env = Environment()
    env.register_agents(*(agents_dict.values()))
    for name in agents_dict:
        to_agents = [agents_dict[to_name] for to_name in config[name]['to_agents']]
        env.register_connections(agents_dict[name], *to_agents)

    env.compile()
    if models_address is not None:
        env.load_models(models_address)
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


def setup_train_test(config_address, data_address, model_from_file=None):
    env, agent_dict = prepare_environment(config_address, model_from_file)
    agents = agent_dict.values()
    input_address = {agent: data_address + agent.name + '_x.csv' for agent in agents}
    output_address = {agent: data_address + agent.name + '_y.csv' for agent in agents}
    data_input = prepare_data(input_address, True)
    data_output = prepare_data(output_address, False)
    return env, agent_dict, data_input, data_output


def setup_prediction(config_address, models_address, data_address):
    env, agent_dict = prepare_environment(config_address, models_address)

    with open(data_address, "r") as f:
        jstring = f.read()
    data_vec_raw = json.loads(jstring)

    def vec_to_df(vec):
        return {key: [vec[key]] for key in vec}

    data_vec = {agent_dict[name]: {
        type: pd.DataFrame(vec_to_df(data_vec_raw[name][type]), dtype=np.float64) for type in ["constants", "states"]
    } for name in data_vec_raw}

    return env, agent_dict, data_vec


def get_aggregator(input_data):
    population = [input_data[agent]["constants"]["const_number"] for agent in input_data if agent.name == "Person"][0]
    return GlobalStateAggregator(population, ["capital", "total_value_destroyed", "unemploymentRate",
                                              "happiness", "valueProduced", "goodwill"])


def learn_input(data_input, data_output, epochs=10 ** 5):
    return env.learn_input(data_output, get_aggregator(data_input), epochs)


if __name__ == '__main__':
    os.chdir('../../..')  # going to the root of the project

    if len(sys.argv) < 2:
        raise Exception("action required")

    action = sys.argv[1]
    if action == 'train':
        env, agent_dict, data_input, data_output = setup_train_test('supplementary/simulation.json', 'supplementary/data/')

        agents = agent_dict.values()
        train_input, train_output, test_input, test_output = train_test_split(data_input, data_output, 0.75)
        env.solo_train(train_input, train_output, training_hyper_params={
            agent: {'epochs': 500} for agent in agents
        })
        env.solo_test(test_input, test_output)
        print("group test:", env.group_test(test_input, test_output, get_aggregator(test_input)))

        if '--group' in sys.argv:
            print("group training:")
            env.group_train(train_input, train_output, get_aggregator(train_input), epochs=500)
            print()

        env.solo_test(test_input, test_output)
        print("group test:", env.group_test(test_input, test_output, get_aggregator(test_input)))
        if '--save' in sys.argv:
            env.save_models("supplementary/models/", data_input)

    elif action == 'input-learning':
        env, agent_dict, data_input, data_output = setup_train_test('supplementary/simulation.json', 'supplementary/data/', 'supplementary/models/')
        learned_input = learn_input(data_input, data_output, epochs=10 ** 5)
        result_json = {
            "constants": {},
            "variables": {}
        }
        for agent in learned_input:
            for row in learned_input[agent]["constants"].rows:
                # todo dataframe to json

    elif action == 'evaluate':
        env, agent_dict, data_input, data_output = setup_train_test('supplementary/simulation.json', 'supplementary/data/', 'supplementary/models/')
        env.solo_test(data_input, data_output)

        print("group test:", env.group_test(data_input, data_output, get_aggregator(data_input)))
        print("learn input:", learn_input(data_input, data_output))

    elif action == 'predict':
        if len(sys.argv) < 3:
            raise Exception("time required!")
        env, agent_dict, data_vec = setup_prediction('supplementary/simulation.json', 'supplementary/models/', 'supplementary/data/data_vec.json')
        data = env.predict_over_time(data_vec, int(sys.argv[2]))

        for agent in data:
            total_data = data[agent]['constants'].join(data[agent]['states'])
            total_data.to_csv(f'supplementary/results/{agent.name}.csv')

    elif action == 'correlation':
        env, agent_dict = prepare_environment('supplementary/simulation.json', 'supplementary/models/')

        for agent_name in agent_dict:
            env.correlation_matrix(agent_dict[agent_name]).to_csv(f'supplementary/results/correlation/{agent_name}.csv')

    elif action == 'derivative':
        if len(sys.argv) < 4:
            raise Exception("both agent and parameter name required")
        env, agent_dict = prepare_environment('supplementary/simulation.json', 'supplementary/models/')

        env.derivative_matrix(agent_dict[sys.argv[2]], sys.argv[3], 100).to_csv(
            f'supplementary/results/derivative/{sys.argv[2]}_{sys.argv[3]}.csv')
