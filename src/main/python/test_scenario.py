import numpy as np
import pandas as pd

from env import Environment, Agent
from node import Node

"""
In this file we examine three simple environments in three different scenarios. T
The notation of S(a) here means the set of state variables for agent type a.
The notation of C(a) here means the set of constants for agent type a
"""


def scenario_one():
    """
    This scenario consists of a single agent type with constant c and state variables x, y, z
    The dynamics of this system are define as:
    # x = y + z + c
    # y = 2z
    # z = sin(x - y)
    """
    env = Environment()
    agent = Agent(['c'], ['x', 'y', 'z'], 'agent1')
    env.register_agents(agent)
    env.compile()

    constants = pd.DataFrame({'c': np.random.rand(1000)})
    states = pd.DataFrame({var: np.random.random(1000) for var in ['x', 'y', 'z']})
    data_input = {agent: {
        'constants': constants,
        'states': states
    }}

    agent_output = pd.DataFrame({
        'x': states['y'] + states['z'] + constants['c'],
        'y': 2 * states['z'],
        'z': np.sin(states['x'] - states['y'])
    })
    data_output = {agent: agent_output}

    env.solo_train(data_input, data_output)

    env.solo_test(data_input, data_output)

    # print(env.derivativeMatrix(agent, 'x', 100))

    print(env.correlation_matrix(agent))


def scenario_two():
    """
    In this scenario we have three agents: a1, a2, and a3
    The relationships diagram is like this (self-loops are assumed for all agents and not represented here)
    a1 => {a2, a3}
    a2 => {a1}
    a3 => {a2}

    C(a1) = {l1, l2}    S(a1) = {x1, x2, x3}
    C(a2) = {m1}        S(a2) = {y1, y2, y3, y4}
    C(a3) = {}          S(a3) = {z1, z2}

    Dynamics of a1:
    x1 = (y1y2)/l1 + e^(-3x1)
    x2 = l2 * ln(|2y4|)
    x3 = x1 - x2

    Dynamics of a2:
    y1 = y4 / 4 + cos(z2)
    y2 = m1y1 + y2
    y3 = cos(z1y2 + z2y1)
    y4 = 1 / (m1 + x3) - floor(x1)

    Dynamics
    z1 = sqrt(3|z2|)
    z2 = e^(-(z1 - 3)^2)
    """
    env = Environment()
    a1 = Agent(['l1', 'l2'], ['x1', 'x2', 'x3'], 'agent1')
    a2 = Agent(['m1'], ['y1', 'y2', 'y3', 'y4'], 'agent2')
    a3 = Agent([], ['z1', 'z2'], 'agent3')
    env.register_agents(a1, a2, a3)
    env.register_connections(a1, a2, a3)
    env.register_connections(a2, a1)
    env.register_connections(a3, a2)
    env.compile()

    a1_constants = {
        'l1': np.random.rand(1000),
        'l2': np.random.rand(1000)
    }

    a1_states = {
        'x1': np.random.rand(1000),
        'x2': np.random.rand(1000),
        'x3': np.random.rand(1000)
    }

    a2_constants = {
        'm1': np.random.rand(1000)
    }

    a2_states = {
        'y1': np.random.rand(1000),
        'y2': np.random.rand(1000),
        'y3': np.random.rand(1000),
        'y4': np.random.rand(1000)
    }

    a3_states = {
        'z1': np.random.rand(1000),
        'z2': np.random.rand(1000)
    }

    data_input = {
        a1: {
            'constants': pd.DataFrame(a1_constants),
            'states': pd.DataFrame(a1_states)
        },
        a2: {
            'constants': pd.DataFrame(a2_constants),
            'states': pd.DataFrame(a2_states)
        },
        a3: {
            'constants': pd.DataFrame(),
            'states': pd.DataFrame(a3_states)
        }
    }

    a1_outputs = {
        'x1': a2_states['y1'] * a2_states['y2'] / a1_constants['l1'] + np.exp(-3 * a1_states['x1']),
        'x2': a1_constants['l2'] * np.log(np.abs(2 * a2_states['y2'])),
        'x3': a1_states['x1'] - a1_states['x2']
    }

    a2_outputs = {
        'y1': a2_states['y2'] / 4 + np.cos(a3_states['z2']),
        'y2': a2_constants['m1'] * a2_states['y1'] + a2_states['y2'],
        'y3': np.cos(a3_states['z1'] * a2_states['y2'] + a3_states['z2'] * a2_states['y1']),
        'y4': 1 / (a2_constants['m1'] + a1_states['x3']) - np.floor(a1_states['x1'])
    }

    a3_outputs = {
        'z1': np.sqrt(3 * np.abs(a3_states['z2'])),
        'z2': np.exp(- np.square(a3_states['z1'] - 3))
    }

    data_output = {
        a1: pd.DataFrame(a1_outputs),
        a2: pd.DataFrame(a2_outputs),
        a3: pd.DataFrame(a3_outputs)
    }

    env.solo_train(data_input, data_output, training_hyper_params={
        a1: {'epochs': 100}, a2: {'epochs': 100}, a3: {'epochs': 50}})

    env.solo_test(data_input, data_output)

    print(env.correlation_matrix(a1))


if __name__ == '__main__':
    scenario_two()
