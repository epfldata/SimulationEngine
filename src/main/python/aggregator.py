from abc import ABC, abstractmethod

import pandas as pd
import tensorflow as tf


class Aggregator(ABC):
    @abstractmethod
    def aggregate(self, output_tensors, n_samples, indices):
        pass

    def aggregate_pd(self, agent_outputs, output_names):
        output_tensors = {agent: tf.constant(agent_outputs[agent].to_numpy()) for agent in
                          agent_outputs}
        n_samples = agent_outputs[list(agent_outputs.keys())[0]].shape[0]
        indices = {agent: {name: i for i, name in enumerate(agent_outputs[agent].columns.values)} for agent in agent_outputs}
        return pd.DataFrame(tf.keras.backend.eval(self.aggregate(output_tensors, n_samples, indices)), columns=output_names)


class GlobalStateAggregator(Aggregator):
    def __init__(self, np_population):
        super().__init__()
        self._np_population = np_population

    """
    When speaking of agents, nodes can also be meant
    """
    def aggregate(self, output_tensors, n_samples, indices):
        dtype = output_tensors[list(output_tensors.keys())[0]].dtype
        average_names = ["capital", "total_value_destroyed", "happiness", "valueProduced", "goodwill"]
        result = {
            average_name: tf.concat([
                tf.reshape(output_tensors[agent][:, indices[agent]["{}.var_{}Mu".format(agent.name, average_name)]], (n_samples, 1))
                for agent in output_tensors if "{}.var_{}Mu".format(agent.name, average_name) in indices[agent]
            ], axis=1)
            for average_name in average_names
        }
        result = {average_name: tf.math.reduce_mean(result[average_name], axis=1) for average_name in average_names}
        employees = tf.concat([tf.reshape(output_tensors[agent][:, indices[agent][agent.name + ".var_employeesMu"]], (n_samples, 1))
                               for agent in output_tensors if agent.name + ".var_employeesMu" in indices[agent]], axis=1)
        employees = tf.math.reduce_sum(employees, axis=1)
        result["unemploymentRate"] = tf.ones(n_samples, dtype) - tf.math.divide(employees, tf.constant(self._np_population.values, dtype))
        return tf.concat([tf.reshape(result[output_name], (n_samples, 1)) for output_name in result], axis=1)


class DummyAggregator(Aggregator):
    def __init__(self):
        super().__init__()

    def aggregate(self, output_tensors, n_samples, indices):
        dtype = output_tensors[list(output_tensors.keys())[0]].dtype
        result = tf.zeros(shape=(n_samples, 2), dtype=dtype)
        p3 = tf.zeros(n_samples, dtype=dtype)
        for agent in output_tensors:
            i_s = indices[agent]
            result += tf.slice(output_tensors[agent], [0, 0], result.shape)
            if "p3" in i_s:
                p3 += output_tensors[agent][:, i_s["p3"]]
        s1 = tf.reshape(result[:, 0], shape=(n_samples, 1))
        s2 = tf.reshape(tf.math.multiply(result[:, 1], p3), shape=(n_samples, 1))
        result = tf.concat([s1, s2], axis=1)
        return result