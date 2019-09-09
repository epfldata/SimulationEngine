import numpy as np
import tensorflow as tf
from tensorflow.python.keras.layers import Dense, InputLayer
from tensorflow.python.keras.models import Sequential, load_model


class Node:

    def __init__(self, agent_name, input_names, output_names, hyper_parameters=None):
        if hyper_parameters is None:
            hyper_parameters = {}
        self.name = agent_name
        self.input_names = input_names
        self.output_names = output_names
        self._model = Sequential()
        units = hyper_parameters.get('number_of_units') or [64] * 3
        activations = hyper_parameters.get('activations') or ['linear'] * 3

        self._model.add(Dense(units[0], input_dim=hyper_parameters.get('features') or 3, activation=activations[0],
                              name=self.name + "-0"))
        for i in range(1, hyper_parameters.get('number_of_layers') or len(units)):
            self._model.add(Dense(units[i] or 64, activation=activations[i], name="{}-{}".format(self.name, i + 1)))

        self._model.compile(loss=hyper_parameters.get('loss') or 'mae',
                            optimizer=hyper_parameters.get('optimizer') or 'sgd',
                            metrics=hyper_parameters.get('metrics') or ['mae'])

    def extended_model(self, n_samples):
        input_variable = tf.Variable(initial_value=tf.ones((n_samples, self.input_size())), trainable=True,
                                     dtype=tf.float32, name=self.name + "-input")
        model = Sequential()
        model.add(InputLayer(input_tensor=input_variable, name=self.name + "-0"))
        for layer in self._model.layers:
            model.add(Dense(layer.units, layer.activation, trainable=False, name=layer.name + "a"))

        model.compile(loss='mae', optimizer='sgd', metrics=['mae'])
        for i in range(len(self._model.layers)):
            model.layers[i].set_weights(self._model.layers[i].get_weights())

        return model

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

    def save_model(self, address):
        self._model.save(address + self.name + '.h5')

    def load_model(self, address):
        self._model = load_model(address + self.name + '.h5')
