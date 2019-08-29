import numpy as np
import pandas as pd
import tensorflow as tf


class Aggregator:
    def __init__(self, tensor_aggregate, numpy_aggregate):
        self._tensor_aggregate = tensor_aggregate
        self._numpy_aggregate = numpy_aggregate

    def aggregate(self, output):
        return self._tensor_aggregate(output)

    def renew_data(self, output):
        return self._numpy_aggregate(output)


class Parameter:
    def __init__(self, node, input_index, output_index):
        self.node = node
        self.input_index = input_index
        self.output_index = output_index


class Graph:
    def __init__(self, nodes, edges):
        self._nodes = nodes.copy()
        self._edges = edges
        self._globals = globals
        self._sess = tf.keras.backend.get_session()

    def group_train(self, train_x, train_s, epochs=100):
        predict_s = self._aggregator.aggregate({node: node.output_tensor() for node in self._nodes})
        loss = tf.losses.mean_squared_error(tf.constant(train_s), predict_s)
        optimizer = tf.train.GradientDescentOptimizer(0.01)
        train = optimizer.minimize(loss)
        for _ in range(epochs):
            train_val, loss_val = self._sess.run((train, loss), feed_dict={
                node.input_tensor(): train_x[node] for node in self._nodes
            })
            print(loss_val)

    def solo_train(self, node, train_x, train_agent_y):
        train_agent_y.columns = node._outputNames
        node.train(self._prepare_node_input(train_x, node), train_agent_y.to_numpy())

    def _prepare_node_input(self, data, node):
        result = data[node]["constants"]
        for in_node in self._edges[node]:
            result = pd.concat([result, data[in_node]["states"]], axis=1)
        result = result.reindex(columns=node._inputNames)
        # todo: remove nan columns
        return result.to_numpy()

    def outputToDF(self, node, nparray):
        return pd.DataFrame(nparray, columns=node._outputNames)

    def predict(self, data, time=1):
        for _ in range(time):
            data = {node: {
                "constants": data[node]["constants"],
                "states": self.outputToDF(node, node.predict(self._prepare_node_input(data, node)))
            } for node in data}
        return data

    def cor(self, p1, p2, data_vec, samples=1000):
        data = {node: data_vec[node].repeat(samples).reshape(samples, len(data_vec[node])) for node in self._nodes}
        data[p1.node] = np.random.normal(size=samples)

        new_data = self.predict(data)

        vec1, vec2 = data[:, p1.input_index], new_data[:, p2.output_index]
        return np.cov([vec1, vec2])[0, 1] / np.sqrt(vec1.var(ddof=1) * vec2.var(ddof=1))

    def inter_node_derivative(self, p1, p2, ds_dy1, globals_position, data):
        dy1_dp1 = p1.node.derivative(data[p1.node])[:, p1.input_index].reshape(-1, 1)
        new_data = self.predict(data)
        ind = globals_position[p2.node]
        dp2_ds = p2.node.derivative(new_data[p2.node])[p2.output_index, ind:].reshape(1, -1)
        return dp2_ds.dot(ds_dy1).dot(dy1_dp1)
