from graph import Graph
from node import Node
import pandas as pd


class Agent:
    def __init__(self, state, name):
        self.state = state
        self.name = name


def _normalize(data):
    scales = {agent: (data[agent].mean(), data[agent].std()) for agent in data}
    scaled_data = {agent: (data[agent] - scales[agent][0]) / scales[agent][1] for agent in data}

    return scaled_data, scales


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
        node = Node({
            'features': sum(len(in_agent.state) for in_agent in self._connections[agent]),
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

        for agent in data:
            data[agent].columns = sorted(data[agent].columns)

        scaled_data, scales = _normalize(data)
        node_indexed_np = {self._get_node(agent): scaled_data[agent].to_numpy() for agent in scaled_data}
        new_node_indexed_np = self._graph.predict(node_indexed_np, time)

        new_data_np = {self._get_agent(node): new_node_indexed_np[node] for node in new_node_indexed_np}
        new_data = {agent: pd.DataFrame(new_data_np[agent], columns=data[agent].columns) for agent in new_data_np}
        rescaled_new_data = {agent: new_data[agent] * scales[agent][1] + scales[agent][0] for agent in new_data}

        return rescaled_new_data

    def solo_train(self, data_input, data_output):
        if self._non_compiled_changes:
            raise Exception("None Compiled Changes! Compile first!")

        scaled_input = _normalize(data_input)[0]
        scaled_output = _normalize(data_output)[0]

        node_input_np = {self._get_node(agent): scaled_input[agent].to_numpy() for agent in scaled_input}
        agent_output_np = {agent: scaled_output[agent].to_numpy() for agent in scaled_output}

        for agent in scaled_input:
            print("Solo Training " + agent.name)
            self._graph.solo_train(self._get_node(agent),
                                   node_input_np,
                                   agent_output_np[agent])
            print()
