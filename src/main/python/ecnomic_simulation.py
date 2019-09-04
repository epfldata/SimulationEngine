import json
from math import floor

import pandas as pd
import numpy as np
from env import Agent, Environment


def prepare_environment(config_address):
    with open(config_address, "r") as sim_file:
        jstring = sim_file.read()
    config = json.loads(jstring)
    agents_dict = {
        agent_name: Agent(config[agent_name]['constants'], config[agent_name]['states'], agent_name,
                          config[agent_name]['hyper_parameters']) for agent_name in config
    }

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


if __name__ == '__main__':
    env, agent_dict = prepare_environment("simulation.json")
    agents = agent_dict.values()

    input_address = {agent: f'data/{agent.name}_x.csv' for agent in agents}
    output_address = {agent: f'data/{agent.name}_y.csv' for agent in agents}

    data_input = prepare_data(input_address, True)
    data_output = prepare_data(output_address, False)

    train_input, train_output, test_input, test_output = train_test_split(data_input, data_output, 0.75)
    env.solo_train(train_input, train_output, training_hyper_params={
        agent: {'epochs': 200} for agent in agents
    })
    env.solo_test(test_input, test_output)
