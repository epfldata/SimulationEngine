import numpy as np
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
    def __init__(self, agent, input_index, output_index):
        self.agent = agent
        self.input_index = input_index
        self.output_index = output_index


class Environment:
    def __init__(self, agents, aggregator=None):
        self._agents = agents.copy()
        self._globals = globals
        self._sess = tf.keras.backend.get_session()
        self._aggregator = aggregator

    def group_train(self, train_x, train_s, epochs=100):
        predict_s = self._aggregator.aggregate({agent: agent.output_tensor() for agent in self._agents})
        loss = tf.losses.mean_squared_error(tf.constant(train_s), predict_s)
        optimizer = tf.train.GradientDescentOptimizer(0.01)
        train = optimizer.minimize(loss)
        for _ in range(epochs):
            train_val, loss_val = self._sess.run((train, loss), feed_dict={
                agent.input_tensor(): train_x[agent] for agent in self._agents
            })
            print(loss_val)

    def solo_train(self, agent, train_x, train_y):
        agent.train(train_x, train_y)

    def predict(self, data, time=1):
        for _ in range(time):
            output = {agent: agent.predict(data[agent]) for agent in data}
            data = self._aggregator.renew_data(output)
        return data

    def cor(self, p1, p2, data_vec, samples=1000):
        data = {agent: data_vec[agent].repeat(samples).reshape(samples, len(data_vec[agent])) for agent in self._agents}
        data[p1.agent] = np.random.normal(size=samples)

        new_data = self.predict(data)

        vec1, vec2 = data[:, p1.input_index], new_data[:, p2.output_index]
        return np.cov([vec1, vec2])[0, 1] / np.sqrt(vec1.var(ddof=1) * vec2.var(ddof=1))

    def inter_agent_derivative(self, p1, p2, ds_dy1, globals_position, data):
        dy1_dp1 = p1.agent.derivative(data[p1.agent])[:, p1.input_index].reshape(-1, 1)
        new_data = self.predict(data)
        ind = globals_position[p2.agent]
        dp2_ds = p2.agent.derivative(new_data[p2.agent])[p2.output_index, ind:].reshape(1, -1)
        return dp2_ds.dot(ds_dy1).dot(dy1_dp1)
