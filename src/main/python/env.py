import numpy as np
import pandas as pd
from graph import Graph
from node import Node


class Agent:
    def __init__(self, constants, states, name, hyper_params=None):
        self.hyper_parameters = {} if hyper_params is None else hyper_params
        self.constants = list(map(lambda n: name + "." + n, constants))
        self.states = list(map(lambda n: name + "." + n, states))
        self.name = name


def _prefix_input_columns_names(data, agent):
    def prefix_column_names(data, agent):
        if type(data) == tuple and type(data[0]) == pd.Series:
            return tuple(
                pd.Series(data[i].values, index=list(map(lambda n: agent.name + "." + n, data[i].index.values))) for i
                in range(len(data)))
        return pd.DataFrame(data.values, columns=list(map(lambda n: agent.name + "." + n, data.columns)))

    return {type: prefix_column_names(data[agent][type], agent) for type in ['states', 'constants']}


def _prefix_output_columns_names(data, agent):
    return pd.DataFrame(data[agent].values, columns=list(map(lambda n: agent.name + "." + n, data[agent].columns)))


class Environment:
    def __init__(self):
        self._agents = []
        self._connections = {}
        self._nodes = []
        self._edges = {}
        self._graph = None
        self._scales = {}
        self._non_compiled_changes = True

    def _normalize_input(self, data, from_previous_scale):
        if from_previous_scale:
            scales = {agent: {
                type: (self._scales[agent][type]['mean'], self._scales[agent][type]['sd'])
                for type in ['states', 'constants']
            } for agent in data}
        else:
            scales = {agent: {
                type: (data[agent][type].mean(), data[agent][type].std()) for type in ['states', 'constants']
            } for agent in data}
        scaled_data = {agent: {
            type: (data[agent][type] - scales[agent][type][0]) / scales[agent][type][1] for type in
            ['states', 'constants']
        } for agent in data}

        return scaled_data, scales

    def _normalize_output(self, data, from_previous_scale):
        if from_previous_scale:
            scales = {
                agent: (self._scales[agent]['states']['mean'], self._scales[agent]['states']['sd'])
                for agent in data
            }
        else:
            scales = {agent: (data[agent].mean(), data[agent].std()) for agent in data}

        scaled_data = {agent: (data[agent] - scales[agent][0]) / scales[agent][1] for agent in data}

        return scaled_data, scales

    def _prefix_and_normalize(self, agent_input, agent_output, from_previous_scale):
        scaled_input = self._normalize_input(agent_input, from_previous_scale)[0]
        scaled_output = self._normalize_output(agent_output, from_previous_scale)[0]
        prefixed_input = {agent: _prefix_input_columns_names(scaled_input, agent) for agent in scaled_input}
        prefixed_output = {agent: _prefix_output_columns_names(scaled_output, agent) for agent in scaled_output}
        return prefixed_input, prefixed_output

    def register_agents(self, *agents):
        for agent in agents:
            self._non_compiled_changes = True
            self._agents.append(agent)
            self._connections[agent] = [agent]

    def register_connections(self, from_agent, *to_agents):
        self._non_compiled_changes = True
        for to_agent in to_agents:
            self._connections[to_agent].append(from_agent)

    def _node_generator(self, agent):
        input_names = agent.constants
        for in_agent in self._connections[agent]:
            input_names += in_agent.states
        node = Node(agent.name, input_names, agent.states, {
            'features': len(input_names),
            'number_of_units': agent.hyper_parameters.get('number_of_units') or [64, 64, 64, len(agent.states)],
            'activations': agent.hyper_parameters.get('activations') or ['relu', 'relu', 'relu', 'linear'],
            'loss': agent.hyper_parameters.get('loss') or 'mae',
            'optimizer': agent.hyper_parameters.get('optimizer') or 'sgd',
            'metrics': agent.hyper_parameters.get('metrics') or ['mae']
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
            raise Exception('Non-Compiled Changes! Compile first!')

        data = {agent: _prefix_input_columns_names(data, agent) for agent in data}

        scaled_data, scales = self._normalize_input(data, from_previous_scale=True)
        node_indexed_df = {self._get_node(agent): scaled_data[agent] for agent in scaled_data}
        new_node_indexed_df = self._graph.predict(node_indexed_df, time)

        new_data = {self._get_agent(node): new_node_indexed_df[node] for node in new_node_indexed_df}
        rescaled_new_data = {agent: {
            type: new_data[agent][type] * scales[agent][type][1] + scales[agent][type][0] for type in
            ['states', 'constants']
        } for agent in new_data}

        return rescaled_new_data

    def predict_over_time(self, data_vec, time):
        if self._non_compiled_changes:
            raise Exception('None Compiled Changes! Compile first!')

        data_vec = {agent: _prefix_input_columns_names(data_vec, agent) for agent in data_vec}

        scaled_data, scales = self._normalize_input(data_vec, from_previous_scale=True)
        node_indexed_vec = {self._get_node(agent): scaled_data[agent] for agent in scaled_data}
        new_node_indexed_df = self._graph.predict_over_time(node_indexed_vec, time)

        new_data = {self._get_agent(node): new_node_indexed_df[node] for node in new_node_indexed_df}
        rescaled_new_data = {agent: {
            type: new_data[agent][type] * scales[agent][type][1] + scales[agent][type][0] for type in
            ['states', 'constants']
        } for agent in new_data}

        return rescaled_new_data

    def group_train(self, agent_input, agent_output, aggregator, epochs=100):
        if self._non_compiled_changes:
            raise Exception('None Compiled Changes! Compile first!')

        agent_input = {agent: _prefix_input_columns_names(agent_input, agent) for agent in agent_input}
        agent_output = {agent: _prefix_output_columns_names(agent_output, agent) for agent in agent_output}

        agent_input = self._normalize_input(agent_input, from_previous_scale=False)[0]
        agent_output = self._normalize_output(agent_output, from_previous_scale=False)[0]

        node_input = {self._get_node(agent): agent_input[agent] for agent in agent_input}
        global_output = aggregator.aggregate_pd(agent_output)
        self._graph.group_train(node_input, global_output, aggregator, epochs)

    def group_test(self, data_input, data_output, aggregator):
        if self._non_compiled_changes:
            raise Exception('None Compiled Changes! Compile first!')

        agent_input, agent_output = self._prefix_and_normalize(data_input, data_output, from_previous_scale=False)
        node_input = {self._get_node(agent): agent_input[agent] for agent in agent_input}
        global_output = aggregator.aggregate_pd(agent_output)
        return self._graph.group_test(node_input, global_output, aggregator)

    def solo_train(self, data_input, data_output, training_hyper_params=None):
        if self._non_compiled_changes:
            raise Exception('Non-Compiled Changes! Compile first!')

        if training_hyper_params is None:
            training_hyper_params = {agent: {
                'epochs': 10,
                'batch_size': 32
            } for agent in data_input}

        data_input = {agent: _prefix_input_columns_names(data_input, agent) for agent in data_input}
        data_output = {agent: _prefix_output_columns_names(data_output, agent) for agent in data_output}

        scaled_input = self._normalize_input(data_input, from_previous_scale=False)[0]
        scaled_output = self._normalize_output(data_output, from_previous_scale=False)[0]

        node_input = {self._get_node(agent): scaled_input[agent] for agent in scaled_input}

        for agent in scaled_input:
            print("Solo Training " + agent.name)
            batch_size = training_hyper_params[agent].get('batch_size') or 32
            epochs = training_hyper_params[agent].get('epochs') or 32
            self._graph.solo_train(self._get_node(agent),
                                   node_input,
                                   scaled_output[agent],
                                   batch_size,
                                   epochs)
            print()

    def learn_input(self, agent_output, aggregator, epochs=100):
        def get_input_scaled(node_input, agent, type, get_names):
            scales = (self._scales[agent][type]['mean'], self._scales[agent][type]['sd'])
            return node_input[get_names(agent)] * scales[1] + scales[0]

        if self._non_compiled_changes:
            raise Exception('Non-Compiled Changes! Compile first!')

        agent_output = {agent: _prefix_output_columns_names(agent_output, agent) for agent in agent_output}
        scaled_output = self._normalize_output(agent_output, from_previous_scale=True)[0]
        global_output = aggregator.aggregate_pd(scaled_output)

        node_input = self._graph.learn_input(global_output, aggregator, epochs)
        agent_input = {
            self._get_agent(node): {
                'states': get_input_scaled(node_input[node], self._get_agent(node), 'states', lambda a: a.states),
                'constants': get_input_scaled(node_input[node], self._get_agent(node), 'constants',
                                              lambda a: a.constants)
            } for node in node_input
        }
        return agent_input

    def solo_test(self, data_input, data_output):
        if self._non_compiled_changes:
            raise Exception("Non-Compiled Changes! Compile First")

        data_input = {agent: _prefix_input_columns_names(data_input, agent) for agent in data_input}
        data_output = {agent: _prefix_output_columns_names(data_output, agent) for agent in data_output}

        scaled_input = self._normalize_input(data_input, from_previous_scale=False)[0]
        scaled_output = self._normalize_output(data_output, from_previous_scale=False)[0]

        node_input = {self._get_node(agent): scaled_input[agent] for agent in scaled_input}

        for agent in scaled_input:
            print("Solo Testing " + agent.name)
            self._graph.solo_test(self._get_node(agent), node_input, scaled_output[agent])

    def correlation_matrix(self, agent, iters=1000):
        if self._non_compiled_changes:
            raise Exception('Non-Compiled Changes! Compile first!')
        node = self._get_node(agent)
        pattern = pd.DataFrame(np.repeat([np.random.normal(size=len(node.input_names))], iters, axis=0),
                               columns=node.input_names)
        cor_matrix = pd.DataFrame(index=node.input_names, columns=node.output_names, dtype=float)
        for param in node.input_names:
            data = pattern
            data[param] = np.random.normal(size=iters)
            targets = pd.DataFrame(node.predict(data), columns=node.output_names)
            cor_matrix.loc[param] = [np.cov([data[param], targets[obs]])[0, 1]
                                     / np.sqrt(data[param].var(ddof=1) * targets[obs].var(ddof=1)) for obs in
                                     node.output_names]
        return cor_matrix

    def derivative_matrix(self, agent, param, iters=100):
        if self._non_compiled_changes:
            raise Exception('Non-Compiled Changes! Compile first!')
        param = agent.name + "." + param
        node = self._get_node(agent)
        data = pd.DataFrame(np.repeat([np.random.normal(size=len(node.input_names))], iters, axis=0),
                            columns=node.input_names)
        data[param] = np.sort(np.random.normal(size=iters))
        data_np = data.to_numpy()
        dMatrix = pd.DataFrame(index=data[param], columns=node.output_names)
        for row, index in enumerate(data[param]):
            dMatrix.loc[index] = node.derivative(data_np[row].reshape(1, node.input_size()))[:,
                                 node.input_names.index(param)]
        return dMatrix

    def save_models(self, address, data):
        if self._non_compiled_changes:
            raise Exception('Non-Compiled Changes! Compile First!')
        for node in self._nodes:
            node.save_model(address)
        for agent in data:
            prefixed_data = _prefix_input_columns_names(data, agent)
            constants = pd.DataFrame({'mean': prefixed_data['constants'].mean(), 'sd': prefixed_data['constants'].std()})
            states = pd.DataFrame({'mean': prefixed_data['states'].mean(), 'sd': prefixed_data['states'].std()})
            constants.to_csv(address + agent.name + '_scale_constants.csv')
            states.to_csv(address + agent.name + '_scale_states.csv')

    def load_models(self, address):
        if self._non_compiled_changes:
            raise Exception('Non-Compiled Changes! Compile First!')
        for node in self._nodes:
            node.load_model(address)
            self._scales[self._get_agent(node)] = {
                'constants': pd.read_csv(address + node.name + '_scale_constants.csv', index_col=0),
                'states': pd.read_csv(address + node.name + '_scale_states.csv', index_col=0)
            }
