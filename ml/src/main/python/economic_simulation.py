import csv
import json
import os
import random
import subprocess

import numpy as np
import pandas as pd
import sys
from aggregator import GlobalStateAggregator
from env import Agent, Environment
from math import floor


def prepare_environment(config_address, data_address, models_address=None):
    """
    loads config and models, creates agents and environment
    """
    def read_header_from_csv(agent_name):
        with open(data_address + agent_name + "_x.csv", "r") as f:
            header = list(csv.reader(f))[0]
            return header

    with open(config_address, 'r') as sim_file:
        jstring = sim_file.read()
    config = json.loads(jstring)
    input_names = {agent_name: read_header_from_csv(agent_name) for agent_name in config}
    constants = {agent_name: list(filter(lambda name: name.startswith("const_"), input_names[agent_name]))
                 for agent_name in input_names}
    states = {agent_name: list(filter(lambda name: name.startswith("var_"), input_names[agent_name]))
              for agent_name in input_names}
    agents_dict = {name: Agent(constants[name], states[name], name, config[name]["hyper_parameters"])
                  for name in input_names}

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
    """
    splits all params in constants and states
    """
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
    """
    splits the input and output by the ratio
    """
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


def setup_train_test(config_address, data_address, eval_address=None, model_from_file=None):
    env, agent_dict = prepare_environment(config_address, data_address, model_from_file)
    agents = agent_dict.values()
    train_input_address = {agent: data_address + agent.name + '_x.csv' for agent in agents}
    train_output_address = {agent: data_address + agent.name + '_y.csv' for agent in agents}
    train_input = prepare_data(train_input_address, True)
    train_output = prepare_data(train_output_address, False)
    if eval_address is None:
        return env, agent_dict, train_input, train_output

    eval_input_address = {agent: eval_address + agent.name + '_x.csv' for agent in agents}
    eval_output_address = {agent: eval_address + agent.name + '_y.csv' for agent in agents}
    eval_input = prepare_data(eval_input_address, True)
    eval_output = prepare_data(eval_output_address, False)
    return env, agent_dict, train_input, train_output, eval_input, eval_output


def setup_prediction(config_address, data_address, models_address, is_batch, data_vec_address=None):
    """
    'data_adress' should be passed to this method in both "batch" and "over time" prediction.
    In the second case, 'data_address' is needed because 'prepare_environment' method uses it
    to figure out the state and constant parameters of each agent.
    """
    env, agent_dict = prepare_environment(config_address, data_address, models_address)

    if is_batch:
        input_address = {agent: data_address + agent.name + '_x.csv' for agent in agent_dict.values()}
        data = prepare_data(input_address, True)
    else:
        with open(data_vec_address, "r") as f:
            jstring = f.read()
        data_vec_raw = json.loads(jstring)

        def vec_to_df(vec):
            return {key: [vec[key]] for key in vec}

        data = {agent_dict[name]: {
            type: pd.DataFrame(vec_to_df(data_vec_raw[name][type]), dtype=np.float64) for type in
            ["constants", "states"]
        } for name in data_vec_raw}

    return env, agent_dict, data


def get_aggregator(input_data):
    population = [input_data[agent]["constants"]["const_number"] for agent in input_data if agent.name == "Person"][0]
    return GlobalStateAggregator(population, ["capital", "total_value_destroyed", "unemploymentRate",
                                              "happiness", "valueProduced", "goodwill"])


def test_all(env, test_input, test_output, from_train_scale=False):
    env.solo_test(test_input, test_output, from_train_scale)
    mae, mse = env.group_test(test_input, test_output, get_aggregator(test_input), from_train_scale)
    print("group test")
    print("MAE", mae)
    print("MSE", mse)


def learn_input(env, data_input, data_output, epochs=1000, learning_rate=0.01):
    """
    learns the input for given output
    :param data_input: contains for each agent constants and states
    :type data_input dict
    :param data_output: contains for each agent constants and states
    :type data_output dict
    :return: the parameter objects for all entries
    """
    learned_input = env.learn_input(data_output, get_aggregator(data_input), epochs, learning_rate)
    result_json = {}
    for agent in learned_input:
        for (i, c_row), (j, s_row) in zip(learned_input[agent]["constants"].iterrows(),
                                          learned_input[agent]["states"].iterrows()):
            if "entry-{}".format(i) not in result_json:
                result_json["entry-{}".format(i)] = {
                    "constants": {},
                    "variables": {}
                }
            result_row = result_json["entry-{}".format(i)]
            result_row["constants"][agent.name] = {column[len("const_"):]: float(c_row[column]) for column
                                                   in learned_input[agent]["constants"].columns}
            result_row["variables"][agent.name] = {column[len("var_"):]: float(s_row[column]) for column
                                                   in learned_input[agent]["states"].columns}

    return result_json


def runCmd(cmd):
    print("Run command:", cmd)
    process = subprocess.Popen(cmd, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    (result, error) = process.communicate()
    rc = process.wait()
    if rc != 0:
        print("Error: failed to execute command:", cmd)
        print(result.decode("utf-8"), error.decode("utf-8"))
        sys.exit(rc)
    return result


def train_and_test(train_input, train_output, eval_input, eval_output, agents, env, group=False):
    env.solo_train(train_input, train_output, training_hyper_params={
        agent: {'epochs': 30} for agent in agents
    })
    test_all(env, eval_input, eval_output)
    if group:
        print("group training:")
        env.group_train(train_input, train_output, get_aggregator(train_input), epochs=100)
        print()
        test_all(env, eval_input, eval_output)


def k_cross_fold_validation(train_input, train_output, k, n, agents, env):
    indices = list(range(n))
    random.seed(19)
    random.shuffle(indices)
    train_input = {agent: {
        type: train_input[agent][type].reindex(index=indices) for type in train_input[agent]
    } for agent in train_input}
    train_output = {agent: train_output[agent].reindex(index=indices) for agent in train_output}
    lower = 0
    upper = int(n / k)
    global_columns = ["capital", "total_value_destroyed", "happiness", "valueProduced", "goodwill", "unemploymentRate"]
    predictions = np.empty(shape=(0, len(global_columns)))
    for i in range(k):
        test_fold_input = {agent: {
            type: train_input[agent][type][lower:upper] for type in train_input[agent]
        } for agent in train_input}
        test_fold_output = {agent: train_output[agent][lower:upper] for agent in train_output}
        train_fold_input = {agent: {
            type: pd.concat([train_input[agent][type][0:lower],
                             train_input[agent][type][upper:n]]) for type in train_input[agent]
        } for agent in train_input}
        train_fold_output = {agent: pd.concat([train_output[agent][0:lower],
                                               train_output[agent][upper:n]]) for agent in train_output}

        print("Fold " + str(i + 1))
        env.compile()
        train_and_test(train_fold_input, train_fold_output, test_fold_input, test_fold_output, agents, env, group=True)
        preds = env.group_predict(test_fold_input, test_fold_output, get_aggregator(test_fold_input), upper - lower)
        predictions = np.concatenate((predictions, preds), axis=0)

        lower += int(n / k)
        if i == k - 2:
        	upper = n
        else:
        	upper += int(n / k)
    mae, mse = env.group_error(train_input, train_output, pd.DataFrame(predictions, columns=global_columns), get_aggregator(train_input))
    print("cross validation errors")
    print("mae", mae)
    print("mse", mse)


def add_target(result_entry, data_address, stepSize=50):
    json_temp = "temp.json"
    targets = np.empty(shape=len(result_entry))
    with open(data_address + "Landlord_x.csv", "r") as f:
        rows = list(csv.reader(f))
        i = 0
        for entry in result_entry:
            result_entry[entry]["constants"]["Landlord"] = {
                rows[0][j].split("_")[1]: float(rows[i + 1][j]) for j in [0, 1]
            }
            f = open(json_temp, "w")
            f.write(json.dumps(result_entry[entry]))
            f.close()
            result = runCmd('sbt --warn "run evaluate temp.json {} {}"'.format(stepSize, entry.split("-")[1]))
            result_entry[entry]["target"] = -float(result.decode("utf-8")[:-1])
            targets[i] = result_entry[entry]["target"]

            i += 1
        result_entry["mean-target"] = targets.mean()
        return result_entry


if __name__ == '__main__':
    os.chdir('../../..')  # going to the root of the project

    if len(sys.argv) < 2:
        raise Exception("action required")

    action = sys.argv[1]
    train_instance_name = "5000-40-5"
    eval_instance_name = "500-5-5"
    if action == 'train':
        env, agent_dict, train_input, train_output, eval_input, eval_output = setup_train_test(
            'supplementary/simulation.json', 'supplementary/data/' + train_instance_name + '/',
            'supplementary/data/' + eval_instance_name + '/')
        agents = agent_dict.values()

        if "-k" in sys.argv:
            i = sys.argv.index("-k")
            k = int(sys.argv[i + 1])
            n = train_input[list(train_input)[0]]["constants"].shape[0]
            if k < 2 or k > n:
                print("k has to be between 2 and n")
                exit(1)
            k_cross_fold_validation(train_input, train_output, k, n, agents, env)
        else:
            train_and_test(train_input, train_output, eval_input, eval_output, agents, env, '--group' in sys.argv)
            if '--save' in sys.argv:
                directory = "supplementary/models/" + train_instance_name + "/"
                if not os.path.exists(directory):
                    os.makedirs(directory)
                env.save_models(directory, train_input)

    elif action == 'input-learning':
        data_address = 'supplementary/data/' + eval_instance_name + '/'
        if "--generate" in sys.argv:
            result_json = {}
            for stepSize in [20, 50, 100]:
                sampleSize = 5
                nSteps = 1
                runCmd("sbt clean compile")
                runCmd('sbt "run generate supplementary/params/params.json {} {} {} all"'.format(sampleSize, nSteps,
                                                                                                 stepSize))

                env, agent_dict, train_input, train_output = setup_train_test('supplementary/simulation.json',
                    'target/data/', model_from_file='supplementary/models/' + train_instance_name + '/')
                result_entry = learn_input(env, train_input, train_output, epochs=10 ** 5, learning_rate=0.02)
                result_json["stepSize-{}".format(stepSize)] = add_target(result_entry, data_address, stepSize)
                f = open("supplementary/params/net-result.json", "w")
                f.write(json.dumps(result_json))
                f.close()
        else:
            env, agent_dict, train_input, train_output = setup_train_test('supplementary/simulation.json',
                data_address, model_from_file='supplementary/models/' + train_instance_name + '/')
            result_entry = learn_input(env, train_input, train_output, epochs=10 ** 5, learning_rate=0.02)
            result_entry = add_target(result_entry, data_address)
            f = open("supplementary/params/net-result.json", "w")
            f.write(json.dumps(result_entry))
            f.close()

    elif action == 'evaluate':
        env, agent_dict, test_input, test_output = setup_train_test('supplementary/simulation.json',
                'supplementary/data/' + eval_instance_name + '/',
                model_from_file='supplementary/models/' + train_instance_name + '/')
        test_all(env, test_input, test_output, True)

    elif action == 'batch-predict':
        if len(sys.argv) < 3:
            raise Exception("time required!")
        env, agent_dict, train_input = setup_prediction('supplementary/simulation.json',
        		'supplementary/data/' + eval_instance_name + '/', 
        		'supplementary/models/' + train_instance_name + '/', True)
        data = env.predict(train_input, int(sys.argv[2]))

        for agent in data:
            total_data = data[agent]['constants'].join(data[agent]['states'])
            total_data.to_csv(f'supplementary/results/batch-prediction/{agent.name}.csv')

    elif action == 'predict-over-time':
        if len(sys.argv) < 3:
            raise Exception("time required!")
        env, agent_dict, data_vec = setup_prediction('supplementary/simulation.json',
        		'supplementary/data/' + eval_instance_name + '/',
                'supplementary/models/' + train_instance_name + '/', 
                False, 'supplementary/data/data_vec.json')
        data = env.predict_over_time(data_vec, int(sys.argv[2]))

        for agent in data:
            total_data = data[agent]['constants'].join(data[agent]['states'])
            total_data.to_csv(f'supplementary/results/prediction-over-time/{agent.name}.csv')


    elif action == 'correlation':
        env, agent_dict = prepare_environment('supplementary/simulation.json', 'supplementary/data/' + eval_instance_name + '/', 
        	'supplementary/models/' + train_instance_name + '/')

        for agent_name in agent_dict:
            env.correlation_matrix(agent_dict[agent_name]).to_csv(f'supplementary/results/correlation/{agent_name}.csv')

    elif action == 'derivative':
        if len(sys.argv) < 4:
            raise Exception("both agent and parameter name required")
        env, agent_dict = prepare_environment('supplementary/simulation.json', 'supplementary/data/' + eval_instance_name + '/',
        	'supplementary/models/' + train_instance_name + '/')

        env.derivative_matrix(agent_dict[sys.argv[2]], sys.argv[3], 100).to_csv(
            f'supplementary/results/derivative/{sys.argv[2]}_{sys.argv[3]}.csv')
