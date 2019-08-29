import numpy as np
import pandas as pd
from graph import Graph
from node import Node


class Agent:
    def __init__(self, constants, state, name):
        self.constants = list(map(lambda n: name + "." + n, constants))
        self.state = list(map(lambda n: name + "." + n, state))
        self.name = name


def _normalizeInput(data):
    scales = {agent: {
        type: (data[agent][type].mean(), data[agent][type].std()) for type in ["states", "constants"]
    } for agent in data}
    scaled_data = {agent: {
        type: (data[agent][type] - scales[agent][type][0]) / scales[agent][type][1] for type in ["states", "constants"]
    } for agent in data}

    return scaled_data, scales

def _normalizeOutput(data):
    scales = {agent: (data[agent].mean(), data[agent].std()) for agent in data}
    scaled_data = {agent: (data[agent] - scales[agent][0]) / scales[agent][1] for agent in data}

    return scaled_data, scales

def _prefixColumnNames(data, agent):
    def prefixColumnNames(data, agent):
        return pd.DataFrame(data.values, columns=list(map(lambda n: agent.name + "." + n, data.columns)))

    return {type: prefixColumnNames(data[agent][type], agent) for type in ["states", "constants"]}


class Environment:
    def __init__(self):
        self._agents = []
        self._connections = {}
        self._nodes = []
        self._edges = {}
        self._graph = None
        self._non_compiled_changes = True

    def register_agent(self, agent):
        self._non_compiled_changes = True
        self._agents.append(agent)
        self._connections[agent] = [agent]

    def register_connection(self, from_agent, to_agent):
        self._non_compiled_changes = True
        self._connections[to_agent].append(from_agent)

    def _node_generator(self, agent):
        inputNames = agent.constants
        for in_agent in self._connections[agent]:
            inputNames += in_agent.state
        node = Node(inputNames, agent.state, {
            'features': len(inputNames),
            'number_of_units': [64, 64, len(agent.state)]
        })
        return node

    def _get_node(self, agent):
        return self._nodes[self._agents.index(agent)]

    def _get_agent(self, node):
        return self._agents[self._nodes.index(node)]

    def compile(self):
        self._nodes = [self._node_generator(agent) for agent in self._agents]
        self._edges = {
            self._get_node(to_agent): [self._get_node(from_agent) for from_agent in self._connections[to_agent]] for
            to_agent in self._agents}
        self._graph = Graph(self._nodes, self._edges)
        self._non_compiled_changes = False

    def predict(self, data, time=1):
        if self._non_compiled_changes:
            raise Exception("Non Compiled Changes! Compile first!")

        data = {agent: _prefixColumnNames(data, agent) for agent in data}

        scaled_data, scales = _normalizeInput(data)
        node_indexed_df = {self._get_node(agent): scaled_data[agent] for agent in scaled_data}
        new_node_indexed_df = self._graph.predict(node_indexed_df, time)

        new_data = {self._get_agent(node): new_node_indexed_df[node] for node in new_node_indexed_df}
        rescaled_new_data = {agent: {
            type: new_data[agent][type] * scales[agent][type][1] + scales[agent][type][0] for type in ["states", "constants"]
        } for agent in new_data}

        return rescaled_new_data

    def solo_train(self, data_input, data_output):
        if self._non_compiled_changes:
            raise Exception("None Compiled Changes! Compile first!")

        data_input = {agent: _prefixColumnNames(data_input, agent) for agent in data_input}

        scaled_input = _normalizeInput(data_input)[0]
        scaled_output = _normalizeOutput(data_output)[0]

        node_input = {self._get_node(agent): scaled_input[agent] for agent in scaled_input}
        agent_output = {agent: scaled_output[agent] for agent in scaled_output}

        for agent in scaled_input:
            print("Solo Training " + agent.name)
            self._graph.solo_train(self._get_node(agent),
                                   node_input,
                                   agent_output[agent])
            print()

    def correlationMatrix(self, agent, iters=1000):
        if self._non_compiled_changes:
            raise Exception("Non Compiled Changes! Compile first!")
        node = self._get_node(agent)
        pattern = pd.DataFrame(np.repeat([np.random.normal(size=len(node._inputNames))], iters, axis=0),
                            columns=node._inputNames)
        corMatrix = pd.DataFrame(index=node._inputNames, columns=node._outputNames)
        for param in node._inputNames:
            data = pattern
            data[param] = np.random.normal(size=iters)
            targets = pd.DataFrame(node.predict(data), columns=node._outputNames)
            corMatrix.loc[param] = [np.cov([data[param], targets[obs]])[0, 1]
                            / np.sqrt(data[param].var(ddof=1) * targets[obs].var(ddof=1)) for obs in node._outputNames]
        return corMatrix
