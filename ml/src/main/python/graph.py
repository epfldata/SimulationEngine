import numpy as np
import pandas as pd
import tensorflow as tf


def _outputToDF(node, nparray):
    """Converts the `nparray` to a DataFrame specific for node"""
    return pd.DataFrame(nparray, columns=node.output_names)


class Graph:
    """The low-level graph compiled from the environment"""

    def __init__(self, nodes, edges):
        """

        :type nodes: list[Node]
        :param edges: an edge from node A to node B means a connection from agent A to agent B in the environment
        :type edges: dict[Node, Node]
        """
        self._nodes = nodes.copy()
        self._edges = edges
        self._sess = tf.keras.backend.get_session()

    def group_train(self, train_x, train_s, aggregator, epochs=100, learning_rate=0.1):
        """Trains all nodes together using aggregated outputs (global statistics)

        :param train_x: The general node indexed and standardized 'data', refer to README.md
        :param train_s: A DataFrame of the global statistics (aggregated states), must be standardized
        :type train_s: pd.DataFrame
        :param aggregator: The aggregator used for aggregating individual states and making global statistics
        :type aggregator: Aggregator
        :param epochs: Number of epochs to train
        """
        indices = {node: {name: i for i, name in enumerate(train_x[node]["states"].columns.values)} for node in train_x}
        predict_s = aggregator.aggregate({node: node.output_tensor() for node in self._nodes}, train_s.shape[0],
                                         indices)
        loss = tf.losses.absolute_difference(tf.constant(train_s.to_numpy(), dtype=tf.float32), predict_s)
        optimizer = tf.train.GradientDescentOptimizer(learning_rate)
        train = optimizer.minimize(loss)
        last_percent = 0
        for i in range(epochs):
            _, loss_val = self._sess.run((train, loss), feed_dict={
                node.input_tensor(): self._prepare_node_input(train_x, node) for node in self._nodes
            })
            if int(i * 100.0 / epochs) > last_percent:
                last_percent = int(i * 100.0 / epochs)
                print(f"{last_percent} %, loss {loss_val}")

    def group_predict(self, test_x, aggregator, n_samples):
        indices = {node: {name: i for i, name in enumerate(test_x[node]["states"].columns.values)} for node in test_x}
        predict_s = aggregator.aggregate({node: node.output_tensor() for node in self._nodes}, n_samples,
                                         indices)
        predictions = self._sess.run(predict_s, feed_dict={
            node.input_tensor(): self._prepare_node_input(test_x, node) for node in self._nodes
        })
        return predictions

    def group_error(self, test_x, test_s):
        """
        Measures the mae and mse for two global observations
        :param test_x: the predicted global observations
        :param test_s: ground truth global observations
        :return: mae, mse
        """
        mae = tf.losses.absolute_difference(tf.constant(test_s), tf.constant(test_x))
        mse = tf.losses.mean_squared_error(tf.constant(test_s), tf.constant(test_x))
        return self._sess.run((mae, mse))

    def group_test(self, test_x, test_s, aggregator):
        """Tests the aggregated output produced by the model against ground truth

        :param test_x: The general node indexed and standardized 'data', refer to README.sd
        :param test_s: A DataFrame containing the ground truth for
        :param aggregator: The aggregator used for aggregating individual states and making global statistics
        :return: MAE value, MSE value
        :rtype: (float, float)
        """
        indices = {node: {name: i for i, name in enumerate(test_x[node]["states"].columns.values)} for node in test_x}
        predict_s = aggregator.aggregate({node: node.output_tensor() for node in self._nodes}, test_s.shape[0], indices)
        mae = tf.losses.absolute_difference(tf.constant(test_s.to_numpy()), predict_s)
        mse = tf.losses.mean_squared_error(tf.constant(test_s.to_numpy()), predict_s)
        mae_val, mse_val = self._sess.run((mae, mse), feed_dict={
            node.input_tensor(): self._prepare_node_input(test_x, node) for node in self._nodes
        })
        return mae_val, mse_val

    def _input_learning_model(self, aggregator, n_samples):
        """Creates the trainable input tensor and connects it to all the relevant models

        :param aggregator: The aggregator used for aggregating individual states and making global statistics
        :param n_samples: Number of samples in the input learning data
        :return: The trainable input tensors and output tensors
        """

        def equal_predictions(extended_model, input_vars):
            """
            Checks for each node if the extended model is the same as the original one

            :param extended_model contains for each node the extended model
            :type extended_model dict
            """
            for node in self._nodes:
                self._sess.run(
                    tf.assign(input_vars[node]['states'], np.random.normal(size=(n_samples, node.state_size())),
                              validate_shape=False))
                self._sess.run(
                    tf.assign(input_vars[node]['constants'], np.random.normal(size=(n_samples, node.constant_size())),
                              validate_shape=False))

            for node in extended_model:
                for i in range(len(node._model.get_weights())):
                    if not np.array_equal(node._model.get_weights()[i], extended_model[node].get_weights()[i]):
                        return False
                output1 = node._model.predict(self._sess.run(self._prepare_node_input(input_vars, node, tf)))
                output2 = self._sess.run(extended_model[node].output)
                if not np.array_equal(np.round(output1, 4), np.round(output2, 4)):
                    return False
            return True

        input_vars = {node: {
            'states': tf.Variable(initial_value=tf.ones((n_samples, node.state_size())), trainable=True,
                                  dtype=tf.float32, name=node.name + '-input-state'),
            'constants': tf.Variable(initial_value=tf.ones((n_samples, node.constant_size())), trainable=True,
                                     dtype=tf.float32, name=node.name + '-input-constants')
        } for node in self._nodes}
        extended_model = {node: node.extended_model(self._prepare_node_input(input_vars, node, tf)) for node in
                          self._nodes}

        # next line is just for debugging
        # assert equal_predictions(extended_model, input_vars)

        for node in self._nodes:
            self._sess.run(tf.assign(input_vars[node]['states'], np.random.normal(size=(n_samples, node.state_size())),
                                     validate_shape=False))
            self._sess.run(
                tf.assign(input_vars[node]['constants'], np.random.normal(size=(n_samples, node.constant_size())),
                          validate_shape=False))

        indices = {node: {name: i for i, name in enumerate(node.output_names)} for node in self._nodes}
        predict_s = aggregator.aggregate({node: extended_model[node].output for node in self._nodes}, n_samples,
                                         indices)
        return input_vars, predict_s

    def learn_input(self, train_s, aggregator, epochs=100, learning_rate=0.01):
        """
        Learns back the input parameters
        :param train_s: target data contains global states
        :return: a dict with a pandas dataframe per node containing the input per sample (row)
        """

        n_samples = train_s.shape[0]
        input_vars, predict_s = self._input_learning_model(aggregator, n_samples)

        loss = tf.losses.absolute_difference(tf.constant(train_s.to_numpy()), predict_s)
        train = tf.train.GradientDescentOptimizer(learning_rate).minimize(loss)

        last_percent = 0
        for i in range(epochs):
            _, l = self._sess.run([train, loss])
            if int(i * 100.0 / epochs) > last_percent:
                last_percent = int(i * 100.0 / epochs)
                print("{} %, loss {}".format(last_percent, l))
        return {node: {
            'states': pd.DataFrame(self._sess.run(input_vars[node]['states']), columns=node.states()),
            'constants': pd.DataFrame(self._sess.run(input_vars[node]['constants']), columns=node.constants())
        } for node in self._nodes}

    def solo_train(self, node, train_x, train_agent_y, batch_size=32, epochs=10):
        train_agent_y = train_agent_y.reindex(columns=node.output_names)
        node.train(self._prepare_node_input(train_x, node), train_agent_y.to_numpy(), batch_size, epochs)

    def solo_test(self, node, test_x, test_agent_y):
        test_agent_y = test_agent_y.reindex(columns=node.output_names)
        return node.test(self._prepare_node_input(test_x, node), test_agent_y.to_numpy())

    def _prepare_node_input(self, data, node, backend=pd):
        """Extracts and returns the input data of the `node` from the general `data`, based on the input connections
           the `node` has

        :param data: The general node indexed and standardized 'data' structure, refer to READEME.md
        :type node: Node
        :param backend: Can be either pandas or tensorflow
        :return: The `node`'s input as a numpy matrix
        :rtype: np.array
        """
        result = data[node]["constants"]
        for in_node in self._edges[node]:
            result = backend.concat([result, data[in_node]["states"]], axis=1)
        if backend == pd:
            result = result.reindex(columns=node.input_names)
            return result.to_numpy()
        else:
            return result

    def predict(self, data, time=1):
        """Predicts the future using the given data

        :param data: The general node indexed and standardized 'data' structure, refer to README.md
        :param time: Predict `time` steps into the future
        :return: The data after `time` steps into the future
        """
        for _ in range(time):
            data = {node: {
                "constants": data[node]["constants"],
                "states": _outputToDF(node, node.predict(self._prepare_node_input(data, node)))
            } for node in data}
        return data

    def predict_over_time(self, data_vec, time):
        """Predicts the future as a time series data

        :param data_vec: The general node indexed and standardized 'data' structure with only a single row,
                         indicating the initial condition
        :param time: Predict `time` steps into the future
        :return: The general 'data' structure, where the ith row indicates data after time i
        """
        data = {node: {
            "constants": pd.DataFrame(columns=data_vec[node]["constants"].columns, dtype=np.float32),
            "states": pd.DataFrame(columns=data_vec[node]["states"].columns, dtype=np.float32)
        } for node in data_vec}
        for _ in range(time):
            data_vec = self.predict(data_vec)
            data = {node: {
                "constants": data[node]["constants"].append(data_vec[node]["constants"], ignore_index=True),
                "states": data[node]["states"].append(data_vec[node]["states"], ignore_index=True)
            } for node in data}
        return data
