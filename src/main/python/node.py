import numpy as np
import tensorflow as tf
from tensorflow.python.keras.layers import Dense
from tensorflow.python.keras.models import Sequential


class Node:

    def __init__(self, inputNames, outputNames, hyper_parameters=None):
        if hyper_parameters is None:
            hyper_parameters = {}
        self._inputNames = inputNames
        self._outputNames = outputNames
        self._model = Sequential()
        units = hyper_parameters.get('number_of_units') or [64] * 3
        activations = hyper_parameters.get('activations') or ['linear'] * 3

        self._model.add(Dense(units[0], input_dim=hyper_parameters.get('features') or 3, activation=activations[0]))
        for i in range(1, hyper_parameters.get('number_of_layers') or 3):
            self._model.add(Dense(units[i] or 64, activation=activations[i]))

        self._model.compile(loss=hyper_parameters.get('loss') or 'mae',
                            optimizer=hyper_parameters.get('optimizer') or 'sgd',
                            metrics=hyper_parameters.get('metrics') or ['mae'])

    def input_size(self):
        return self._model.input.shape[1]

    def state_size(self):
        return self._model.output.shape[1]

    def output_tensor(self):
        return self._model.output

    def input_tensor(self):
        return self._model.input

    def train(self, predictors, targets, batch_size=32, epochs=10):
        self._model.fit(predictors, targets,
                        batch_size=batch_size,
                        epochs=epochs)

    def test(self, predictors, targets):
        return self._model.evaluate(predictors, targets)

    def predict(self, predictors):
        return self._model.predict(predictors)

    def derivative(self, predictors):
        grads = []
        outputs = self._model.output.shape[1]
        features = self._model.input.shape[1]

        for i in range(outputs):
            gradient = tf.gradients(self._model.output[0][i], self._model.input)[0]
            grads.append(tf.reshape(gradient, (1, features)))

        return tf.keras.backend.get_session().run(tf.concat(grads, axis=0), feed_dict={self._model.input: predictors})

    def cor(self, i, j, predictors_vec, samples=1000):
        predictors = predictors_vec.repeat(samples).reshape(samples, len(predictors_vec))
        predictors[:, i] = np.random.uniform(-1, 1, samples)

        targets = self.predict(predictors)

        return np.cov([predictors[:, i], targets[:, j]])[0, 1] / np.sqrt(
            predictors[:, i].var(ddof=1) * targets[:, j].var(ddof=1))
