import numpy as np
import pandas as pd
from graph import Graph
from node import Node


class Agent:
    """An agent, indicating an agent type in the high-level environment"""

    def __init__(self, constants, states, name, hyper_params=None):
        """

        :param constants: Constant parameters of the agent
        :type constants: list[str]
        :param states: State-defining (variable) parameters of the agent
        :type states: list[str]
        :param name: Name of the agent
        :type name: str
        :param hyper_params: A dictionary from hyper parameter name to value, Hyper Parameters of the underlying neural
                             network
        :type hyper_params: dict[str, optional]
        """
        self.hyper_parameters = {} if hyper_params is None else hyper_params
        self.constants = list(map(lambda n: name + "." + n, constants))
        self.states = list(map(lambda n: name + "." + n, states))
        self.name = name


def _prefix_input_columns_names(data, agent):
    """

    :param data: The general 'data' structure, refer to README.md
    :param agent: The agent to prefix its data
    :type agent: Agent
    :return: The agent specific data where columns in DataFrames are prefixed by the agent's name
    :rtype: dict[str, pd.DataFrame]
    """

    def prefix_column_names(data, agent):
        if type(data) == tuple and type(data[0]) == pd.Series:
            return tuple(
                pd.Series(data[i].values, index=list(map(lambda n: agent.name + "." + n, data[i].index.values))) for i
                in range(len(data)))
        return pd.DataFrame(data.values, columns=list(map(lambda n: agent.name + "." + n, data.columns)))

    return {type: prefix_column_names(data[agent][type], agent) for type in ['states', 'constants']}


def _prefix_output_columns_names(data, agent):
    """

    :param data: A dictionary from agents to their state data
    :type data: dict[Agent, pd.DataFrame]
    :param agent: The agent to prefix its data
    :type agent: Agent
    :return: A new DataFrame where the columns are prefixed by the agent's name
    :rtype: pd.DataFrame
    """
    return pd.DataFrame(data[agent].values, columns=list(map(lambda n: agent.name + "." + n, data[agent].columns)))


class Environment:
    """The high-level Environment"""

    def __init__(self):
        self._agents = []
        self._connections = {}
        self._nodes = []
        self._edges = {}
        self._graph = None
        self._scales = {}
        self._non_compiled_changes = True

    def _normalize_input(self, data, from_previous_scale):
        """Returns the standardized (z-score) data

        :param data: The general 'data' structure, refer to README.md
        :param from_previous_scale: If true, self._scales' mean and sd are used, otherwise they are extracted from data
        :type from_previous_scale: bool
        :return: standardized (z-score) data, the data has the same 'data' structure of the project, along with the
                 scales
        """
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

        scaled_data = {agent: {type: scaled_data[agent][type].fillna(1) for type in ['states', 'constants']} for agent
                       in scaled_data}

        return scaled_data, scales

    def _normalize_output(self, data, from_previous_scale):
        """Returns the standardized (z-score) data

        :param data: A dictionary from agents to their state data
        :type data: dict[Agent, pd.DataFrame]
        :param from_previous_scale: If true, self._scales' mean and sd are used, otherwise they are extracted from data
        :type from_previous_scale: bool
        :return: Standardized (z-score) data, along with the scales
        :rtype: (dict[Agent, pd.DataFrame], dict[Agent, (pd.Series, pd.Series)])
        """
        if from_previous_scale:
            scales = {
                agent: (self._scales[agent]['states']['mean'], self._scales[agent]['states']['sd'])
                for agent in data
            }
        else:
            scales = {agent: (data[agent].mean(), data[agent].std()) for agent in data}

        scaled_data = {agent: (data[agent] - scales[agent][0]) / scales[agent][1] for agent in data}

        scaled_data = {agent: scaled_data[agent].fillna(1) for agent in scaled_data}

        return scaled_data, scales

    def _prefix_and_normalize(self, agent_input, agent_output, from_previous_scale):
        """

        :param agent_input: The general 'data' structure, refer to README.md
        :param agent_output: A dictionary from agents to their output (state) data
        :type agent_output: dict[Agent, pd.DataFrame]
        :param from_previous_scale: If true, self._scales' mean and sd are used, otherwise they are extracted from data
        :type from_previous_scale: bool
        :return: prefixed input and output with their respective structures
        """
        prefixed_input = {agent: _prefix_input_columns_names(agent_input, agent) for agent in agent_input}
        prefixed_output = {agent: _prefix_output_columns_names(agent_output, agent) for agent in agent_output}
        scaled_input = self._normalize_input(prefixed_input, from_previous_scale)[0]
        scaled_output = self._normalize_output(prefixed_output, from_previous_scale)[0]
        return scaled_input, scaled_output

    def register_agents(self, *agents):
        """

        :param agents: a list of agents to register in the environment
        :type agents: varargs of Agent
        """
        for agent in agents:
            self._non_compiled_changes = True
            self._agents.append(agent)
            self._connections[agent] = [agent]

    def register_connections(self, from_agent, *to_agents):
        """

        :param from_agent: sender of the information
        :type from_agent: Agent
        :param to_agents: all receivers of the information
        :type to_agents: varargs of Agent
        """
        self._non_compiled_changes = True
        for to_agent in to_agents:
            self._connections[to_agent].append(from_agent)

    def _node_generator(self, agent):
        """

        :type agent: Agent
        :return: Creates and returns the low-level node corresponding to the high-level agent
        :rtype: Node
        """
        input_names = agent.constants.copy()
        for in_agent in self._connections[agent]:
            input_names += in_agent.states
        node = Node(agent.name, input_names, agent.states, {
            'features': len(input_names),
            'number_of_units': agent.hyper_parameters.get('number_of_units') or [64, 64, 64],
            'activations': agent.hyper_parameters.get('activations') or ['relu', 'relu', 'relu'],
            'loss': agent.hyper_parameters.get('loss') or 'mae',
            'optimizer': agent.hyper_parameters.get('optimizer') or 'sgd',
            'metrics': agent.hyper_parameters.get('metrics') or ['mae', 'mse']
        })
        return node

    def _get_node(self, agent):
        """

        :type agent: Agent
        :return: Finds and returns the Node Corresponding to the agent
        :rtype: Node
        """
        return self._nodes[self._agents.index(agent)]

    def _get_agent(self, node):
        """

        :type node: Node
        :return: Finds and returns the agent corresponding to the node
        :rtype: Agent
        """
        return self._agents[self._nodes.index(node)]

    def compile(self):
        """Compiles the high-level environment and creates the low-level graph and its nodes"""

        self._nodes = [self._node_generator(agent) for agent in self._agents]
        self._edges = {
            self._get_node(to_agent): [self._get_node(from_agent) for from_agent in self._connections[to_agent]] for
            to_agent in self._agents}
        self._graph = Graph(self._nodes, self._edges)
        self._non_compiled_changes = False

    def predict(self, data, time=1):
        """Predicts the future using the given data

        :param data: The general 'data' structure, refer to README.md
        :param time: Predict `time` steps into the future
        :return: The data after `time` steps into the future
        """
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
        """Predicts the future as a time series data

        :param data_vec: The general 'data' structure with only a single row, indicating the initial condition
        :param time: Predict `time` steps into the future
        :return: The general 'data' structure, where the ith row indicates data after time i
        """
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

    def group_train(self, agent_input, agent_output, aggregator, epochs=100, learning_rate=0.1):
        """Trains all agents together using aggregated outputs (global statistics)

        :param agent_input: The general 'data' structure, refer to README.md
        :param agent_output: A dictionary from agents to their output states
        :type agent_output: dict[Agent, pd.DataFrame]
        :param aggregator: The aggregator used for producing global statistics to train from
        :type aggregator: Aggregator
        :param epochs: Number of epochs to train
        """
        if self._non_compiled_changes:
            raise Exception('None Compiled Changes! Compile first!')

        agent_input = {agent: _prefix_input_columns_names(agent_input, agent) for agent in agent_input}
        agent_output = {agent: _prefix_output_columns_names(agent_output, agent) for agent in agent_output}

        agent_input = self._normalize_input(agent_input, from_previous_scale=False)[0]
        agent_output = self._normalize_output(agent_output, from_previous_scale=False)[0]

        node_input = {self._get_node(agent): agent_input[agent] for agent in agent_input}
        global_output = aggregator.aggregate_pd(agent_output)
        self._graph.group_train(node_input, global_output, aggregator, epochs, learning_rate)

    def group_test(self, data_input, data_output, aggregator, from_train_scale=False):
        """Tests the aggregated output produced by the model against ground truth

        :param data_input: The general 'data' structure, refer to README.md
        :param data_output: A dictionary from agents to the ground truth value of their states
        :param aggregator: The aggregator used for producing the global statistics from agent states
        :return: MAE value, MSE value
        :rtype: (float, float)
        """
        if self._non_compiled_changes:
            raise Exception('None Compiled Changes! Compile first!')

        agent_input, agent_output = self._prefix_and_normalize(data_input, data_output,
                                                               from_previous_scale=from_train_scale)
        node_input = {self._get_node(agent): agent_input[agent] for agent in agent_input}
        global_output = aggregator.aggregate_pd(agent_output)
        return self._graph.group_test(node_input, global_output, aggregator)

    def group_predict(self, data_input, data_output, aggregator, n_samples, from_train_scale=False):
        """Predicts global outcome

        :param data_input: The general 'data' structure, refer to README.md
        :param data_output: A dictionary from agents to the ground truth value of their states
        :param aggregator: The aggregator used for producing the global statistics from agent states
        :param n_samples: The number of samples
        :param from_train_scale: If true, self._scales' mean and sd are used, otherwise they are extracted from data
        :return: Loss value
        :rtype: float
        """
        if self._non_compiled_changes:
            raise Exception('None Compiled Changes! Compile first!')
        agent_input, _ = self._prefix_and_normalize(data_input, data_output,
                                                               from_previous_scale=from_train_scale)
        node_input = {self._get_node(agent): agent_input[agent] for agent in agent_input}
        return self._graph.group_predict(node_input, aggregator, n_samples)


    def group_error(self, data_input, data_output, predictions, aggregator, from_train_scale=False):
        """Measures the error between global predictions and ground truth

        :param data_input: The general 'data' structure, refer to README.md
        :param data_output: A dictionary from agents to the ground truth value of their states
        :param predictions: A dataframe with predicted global observations
        :param aggregator: The aggregator used for producing the global statistics from agent states
        :param from_train_scale: If true, self._scales' mean and sd are used, otherwise they are extracted from data
        :return: mae, mse
        :rtype: (float, float)
        """
        agent_input, agent_output = self._prefix_and_normalize(data_input, data_output,
                                                               from_previous_scale=from_train_scale)
        global_output = aggregator.aggregate_pd(agent_output)
        return self._graph.group_error(global_output.to_numpy(), predictions.to_numpy())


    def solo_train(self, data_input, data_output, training_hyper_params=None):
        """Trains each agent individually using the loss function from its own state

        :param data_input: The general 'data' structure, refer to README.md
        :param data_output: A dictionary from agents to their output states
        :type data_output: dict[Agent, pd.DataFrame]
        :param training_hyper_params: A dictionary from agents to another dictionary, the second one sets the two
                                      training hyper parameters of the agent. These two hyper parameters are number of
                                      epochs and batch size
        """
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

    def learn_input(self, agent_output, aggregator, epochs=100, learning_rate=0.01):
        """
        Learns back the inputs that produce the given output
        :param agent_output: contains per agent its output
        :type agent_output dict
        :return: rescaled input data per agent
        """

        def get_input_scaled(node_input, agent, type):
            scales = (self._scales[agent][type]['mean'], self._scales[agent][type]['sd'])
            return node_input * scales[1] + scales[0]

        if self._non_compiled_changes:
            raise Exception('Non-Compiled Changes! Compile first!')

        prefixed_agent_output = {agent: _prefix_output_columns_names(agent_output, agent) for agent in agent_output}
        scaled_output = self._normalize_output(prefixed_agent_output, from_previous_scale=True)[0]
        global_output = aggregator.aggregate_pd(scaled_output)

        node_input = self._graph.learn_input(global_output, aggregator, epochs, learning_rate)

        agent_input = {
            self._get_agent(node): {
                'states': get_input_scaled(node_input[node]['states'], self._get_agent(node), 'states'),
                'constants': get_input_scaled(node_input[node]['constants'], self._get_agent(node), 'constants')
            } for node in node_input
        }
        for agent in agent_input:
            agent_input[agent]['constants'].columns = [name[len(agent.name) + 1:] for name in
                                                       agent_input[agent]['constants'].columns]
            agent_input[agent]['states'].columns = [name[len(agent.name) + 1:] for name in
                                                    agent_input[agent]['states'].columns]
        return agent_input

    def solo_test(self, data_input, data_output, from_train_scale=False):
        """

        :param data_input: The general 'data' structure, refer to README.md
        :param data_output: A dictionary from agents to the ground truth value of their states
        :return:
        """
        if self._non_compiled_changes:
            raise Exception("Non-Compiled Changes! Compile First")

        data_input = {agent: _prefix_input_columns_names(data_input, agent) for agent in data_input}
        data_output = {agent: _prefix_output_columns_names(data_output, agent) for agent in data_output}

        scaled_input = self._normalize_input(data_input, from_previous_scale=from_train_scale)[0]
        scaled_output = self._normalize_output(data_output, from_previous_scale=from_train_scale)[0]

        node_input = {self._get_node(agent): scaled_input[agent] for agent in scaled_input}

        for agent in scaled_input:
            print("Solo Testing " + agent.name)
            self._graph.solo_test(self._get_node(agent), node_input, scaled_output[agent])

    def correlation_matrix(self, agent, iters=1000):
        """Returns the correlation matrix between the agent inputs and outputs

        :type agent: Agent
        :param iters: Number of samples to estimate the correlation from
        :return: The correlation matrix, the columns represent outputs and the rows represent inputs
        :rtype: pd.DataFrame
        """
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
        """Returns the derivative matrix for the `param` parameter of the agent

        :type agent: Agent
        :type param: str
        :param iters: Number of sample points to evaluate the derivative at
        :return: The derivative matrix, where each columns represents an input of the agent and each row is one sample
                 point
        """
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
        """Saves the neural network models and standardization scales in the given address

        :param address: The address of the directory where the models and scales for standardizing data are stored
        :type address: str
        :param data: The entire data to compute scales (mean and sd) from
        """
        if self._non_compiled_changes:
            raise Exception('Non-Compiled Changes! Compile First!')
        for node in self._nodes:
            node.save_model(address)
        for agent in data:
            prefixed_data = _prefix_input_columns_names(data, agent)
            constants = pd.DataFrame(
                {'mean': prefixed_data['constants'].mean(), 'sd': prefixed_data['constants'].std()})
            states = pd.DataFrame({'mean': prefixed_data['states'].mean(), 'sd': prefixed_data['states'].std()})
            constants.to_csv(address + agent.name + '_scale_constants.csv')
            states.to_csv(address + agent.name + '_scale_states.csv')

    def load_models(self, address):
        """Loads the neural network models and standardization scales from the given address

        :param address: The address of the directory where the models and scales are stored
        :type address: str
        """
        if self._non_compiled_changes:
            raise Exception('Non-Compiled Changes! Compile First!')
        for node in self._nodes:
            node.load_model(address)
            self._scales[self._get_agent(node)] = {
                'constants': pd.read_csv(address + node.name + '_scale_constants.csv', index_col=0),
                'states': pd.read_csv(address + node.name + '_scale_states.csv', index_col=0)
            }
