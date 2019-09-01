import numpy as np
import pandas as pd
import tensorflow as tf


def _outputToDF(node, nparray):
    return pd.DataFrame(nparray, columns=node._outputNames)


class Aggregator:
    def aggregate(self, outputTensors):
        # s1, s2
        result = tf.zeros(shape=(100, 2), dtype=tf.float32)
        p3 = tf.zeros(100)
        for key in outputTensors:
            result += tf.slice(outputTensors[key], [0, 0], result.shape)
            if (outputTensors[key].shape[1] == 3):
                p3 += outputTensors[key][:, 2]
        s1 = tf.reshape(result[:, 0], shape=(100, 1))
        s2 = tf.reshape(tf.math.multiply(result[:, 1], p3), shape=(100, 1))
        result = tf.concat([s1, s2], axis=1)
        return result

    def aggregate_pd(self, agentOutputs, outputNames):
        outputTensors = {agent: tf.constant(agentOutputs[agent].to_numpy(), dtype=tf.float32) for agent in agentOutputs}
        return pd.DataFrame(tf.keras.backend.eval(self.aggregate(outputTensors)), columns=outputNames)



class Parameter:
    def __init__(self, node, input_index, output_index):
        self.node = node
        self.input_index = input_index
        self.output_index = output_index


class Graph:
    def __init__(self, nodes, edges):
        self._nodes = nodes.copy()
        self._edges = edges
        self._sess = tf.keras.backend.get_session()

    def group_train(self, train_x, train_s, aggregator, epochs=100):
        predict_s = aggregator.aggregate({node: node.output_tensor() for node in self._nodes})
        loss = tf.losses.mean_squared_error(tf.constant(train_s.to_numpy()), predict_s)
        optimizer = tf.train.GradientDescentOptimizer(0.01)
        train = optimizer.minimize(loss)
        for i in range(epochs):
            train_val, loss_val = self._sess.run((train, loss), feed_dict={
                node.input_tensor(): self._prepare_node_input(train_x, node) for node in self._nodes
            })
            print("Epoch "  + str(i) + ", loss:", loss_val)

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

    def predict(self, data, time=1):
        for _ in range(time):
            data = {node: {
                "constants": data[node]["constants"],
                "states": _outputToDF(node, node.predict(self._prepare_node_input(data, node)))
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
