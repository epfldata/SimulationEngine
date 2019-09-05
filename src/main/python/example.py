import numpy as np
import pandas as pd
from env import Environment, Agent
from graph import Aggregator


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

    env.register_agents(agent1)
    env.register_agents(agent2)
    env.register_connections(agent1, agent2)
    env.compile()

    env.solo_train(data, output)
    aggregator = Aggregator()
    env.group_train(data, aggregator.aggregate_pd(output, ["s1", "s2"]), aggregator)

    global_output = pd.DataFrame(np.array([np.arange(0, 100), np.arange(0, 100)]).transpose())
    env.learn_input(global_output, aggregator, epochs=10 ** 6)

    print("correlation matrix agent2\n", env.correlation_matrix(agent2))
    print("derivative matrix agent2\n", env.derivative_matrix(agent1, "c1", 100))
    print(env.predict(data)[agent2]["states"])
