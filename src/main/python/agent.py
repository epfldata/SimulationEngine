import numpy as np
import tensorflow as tf
from tensorflow.python.keras.layers import Dense
from tensorflow.python.keras.models import Sequential


class Agent:

    def __init__(self, hyper_parameters=None):
        if hyper_parameters is None:
            hyper_parameters = {}
        self._model = Sequential()
        units = hyper_parameters.get('number_of_units') or [64] * 4
        activations = hyper_parameters.get('activations') or ['linear'] * 4

        self._model.add(Dense(units[0], input_dim=hyper_parameters.get('features') or 3, activation=activations[0]))
        for i in range(1, hyper_parameters.get('number_of_layers') or 3):
            self._model.add(Dense(units[i] or 64, activation=activations[i]))

        self._model.compile(loss=hyper_parameters.get('loss') or 'mae',
                            optimizer=hyper_parameters.get('optimizer') or 'sgd',
                            metrics=hyper_parameters.get('metrics') or ['mae'])

    def train(self, predictor, target, batch_size=32, epochs=10):
        self._model.fit(predictor, target,
                        batch_size=batch_size,
                        epochs=epochs)

    def test(self, predictor, target):
        self._model.evaluate(predictor, target)

    def predict(self, predictor):
        return self._model.predict(predictor)

    def derivative(self, predictor):
        grads = []
        outputs = self._model.output.shape[1]
        features = self._model.input.shape[1]

        for i in range(outputs):
            gradient = tf.gradients(self._model.output[0][i], self._model.input)[0]
            grads.append(tf.reshape(gradient, (1, features)))
        return tf.keras.backend.get_session().run(tf.concat(grads, axis=0), feed_dict={self._model.input: predictor})


if __name__ == '__main__':
    hyper_parameters = {
        'features': 4,
        'number_of_units': [64, 64, 1],
        'activations': ['relu', 'relu', 'linear'],
        'number_of_layers': 3
    }
    agent = Agent(hyper_parameters)
    train_x = np.random.rand(10000, 4)

    train_y = 3 * np.sin(train_x[:, :-1].sum(axis=1))
    agent.train(train_x, train_y, epochs=1000)

    # print("test")
    test_x = np.random.rand(1000, 4)
    test_y = 3 * np.sin(test_x[:, :-1].sum(axis=1))
    agent.test(test_x, test_y)
    print(agent.derivative(np.ones((1, 4))))
