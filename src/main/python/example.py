import numpy as np
import pandas as pd
import tensorflow as tf
from env import Environment, Agent
from graph import Graph, Aggregator, Parameter
from node import Node


def agent_example():
    hyper_parameters = {
        'features': 4,
        'number_of_units': [64, 64, 1],
        'activations': ['relu', 'relu', 'linear'],
        'number_of_layers': 3
    }
    agent = Node(hyper_parameters)
    train_x = np.random.rand(1000, 4)

    train_y = 3 * np.sin(train_x[:, :-1].sum(axis=1))
    # train_y = train_x[:, :-1].sum(axis=1)
    agent.train(train_x, train_y, epochs=100)

    # print("test")
    test_x = np.random.rand(100, 4)
    test_y = 3 * np.sin(test_x[:, :-1].sum(axis=1))
    # test_y = test_x[:, :-1].sum(axis=1)
    agent.test(test_x, test_y)
    print(agent.derivative(np.ones((1, 4))))
    print()
    print()
    print(agent.cor(0, 0, np.random.rand(4)))


def env_example():
    agent1 = Node({
        'features': 4,
        'number_of_units': [64, 64, 3]
    })
    agent2 = Node({
        'features': 4,
        'number_of_units': [64, 32, 3],
        'activations': ['tanh', 'relu', 'linear']
    })
    agents = [agent1, agent2]

    def np_aggregator(output):
        global_state = sum(output.values()).sum(axis=1).reshape(-1, 1)
        return {agent: np.concatenate([output[agent], global_state], axis=1) for agent in output}

    def tf_aggregator(output):
        return tf.reduce_sum(tf.add_n(list(output.values())), axis=1, keepdims=True)

    aggregator = Aggregator(tf_aggregator, np_aggregator)
    env = Graph(agents, aggregator)
    train_x = {agent: np.random.rand(100, 4) for agent in agents}
    train_y = {agent1: np.random.rand(100, 3),
               agent2: np.random.rand(100, 3)}
    train_s = np.random.rand(100, 1)

    env.solo_train(agent1, train_x[agent1], train_y[agent1])
    env.solo_train(agent2, train_x[agent2], train_y[agent2])

    env.group_train(train_x, train_s)

    data = {agent1: np.random.rand(1, 4), agent2: np.random.rand(1, 4)}

    p1 = Parameter(agent1, 0, 0)
    p2 = Parameter(agent2, 0, 0)

    global_positions = {agent1: 3, agent2: 3}

    ds_dy = np.random.rand(1, 3)
    print(env.inter_node_derivative(p1, p2, ds_dy, global_positions, data))


if __name__ == '__main__':
    env = Environment()
    agent1 = Agent(["c1", "c2"], ['p1', 'p2'], 'agent1')
    agent2 = Agent(["c1", "c2"], ['p1', 'p2', 'p3'], 'agent2')
    data = {
        agent1: {
            "states": pd.DataFrame({'p1': np.random.rand(100), 'p2': np.random.rand(100)}),
            "constants": pd.DataFrame({"c1": np.random.rand(100), "c2": np.random.rand(100)})
        },
        agent2: {
            "states": pd.DataFrame({'p1': np.random.rand(100), 'p2': np.random.rand(100), 'p3': np.random.rand(100)}),
            "constants": pd.DataFrame({"c1": np.random.rand(100), "c2": np.random.rand(100)})
        }
    }

    output = {
        agent1: pd.DataFrame({'p1': np.random.rand(100), 'p2': np.random.rand(100)}),
        agent2: pd.DataFrame({'p1': np.random.rand(100), 'p2': np.random.rand(100), 'p3': np.random.rand(100)})
    }

    env.register_agent(agent1)
    env.register_agent(agent2)
    env.register_connection(agent1, agent2)
    env.compile()

    env.solo_train(data, output)
    print(env.predict(data)[agent2]["states"])
