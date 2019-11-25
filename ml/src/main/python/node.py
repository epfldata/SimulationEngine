import tensorflow as tf
from tensorflow.keras.layers import Dense, InputLayer
from tensorflow.keras.models import Sequential, load_model


class Node:

    def __init__(self, agent_name, input_names, output_names, hyper_parameters=None):
        """

        :type agent_name: str
        :type input_names: list[str]
        :type output_names: list[str]
        :param hyper_parameters: The neural network's hyper parameters, it can contain:
                    number_of_units:
                            list[int] - the ith element indicates the number of units to use in the ith layer
                                        (without the output layer)
                            default: [64, 64, 64]
                    activations:
                            list[str] - the ith element indicates the activation function to use in the ith layer
                            default: ['linear', 'linear', 'linear']
                    features:
                            int - the number of features the neural network has, must be the same len(input_names)
                            default: 3
                    number_of_layers:
                            int - number of layers (excluding input and output) to use
                            default: if number_of_units provided its length, otherwise 3
                    loss:
                            str - loss function used for training
                            default: mean absolute error
                    optimizer:
                            str - optimizer used for training
                            default: stochastic gradient decent
                    metrics:
                            list[str] - the list of metrics to use for evaluation
                            default: ['mae']
        :type hyper_parameters: dict[str, optional]
        """
        if hyper_parameters is None:
            hyper_parameters = {}
        self.name = agent_name
        self.input_names = input_names
        self.output_names = output_names
        self._model = Sequential()
        units = hyper_parameters.get('number_of_units') or [64] * 3
        activations = hyper_parameters.get('activations') or ['relu'] * 3

        self._model.add(Dense(units[0], input_dim=hyper_parameters.get('features') or len(input_names), activation=activations[0],
                              name=self.name + "-0"))
        for i in range(1, hyper_parameters.get('number_of_layers') or len(units)):
            self._model.add(Dense(units[i] or 64, activation=activations[i], name="{}-{}".format(self.name, i)))
        self._model.add(Dense(len(output_names), activation="linear", name="{}-{}".format(self.name, len(units) + 1)))

        self._model.compile(loss=hyper_parameters.get('loss') or 'mae',
                            optimizer=hyper_parameters.get('optimizer') or 'sgd',
                            metrics=hyper_parameters.get('metrics') or ['mae'])

    def extended_model(self, input_variable):
        """Creates a non-trainable copy of the model for input learning

        :param input_variable: The trainable input tensor of the model
        :type input_variable: tf.Tensor
        :return: The non-trainable copy of model
        """
        model = Sequential()
        model.add(InputLayer(input_tensor=input_variable, name=self.name + "-in-var"))
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

    def constant_size(self):
        return len(self.constants())

    def output_tensor(self):
        return self._model.output

    def input_tensor(self):
        return self._model.input

    def states(self):
        return [name for name in self.input_names if f'{self.name}.var_' in name]

    def constants(self):
        return [name for name in self.input_names if 'const_' in name]

    def train(self, predictors, targets, batch_size=32, epochs=10):
        """

        :type predictors: np.array
        :type targets: np.array
        :param batch_size: Batch size used for training
        :param epochs: Number of epochs used for training
        """
        self._model.fit(predictors, targets,
                        batch_size=batch_size,
                        epochs=epochs)

    def test(self, predictors, targets):
        """

        :type predictors: np.array
        :type targets: np.array
        :return: The list of metrics used for evaluation
        :rtype: float
        """
        return self._model.evaluate(predictors, targets)

    def predict(self, predictors):
        """

        :type predictors: np.array
        :rtype: np.array
        """
        return self._model.predict(predictors)

    def derivative(self, predictors):
        """Returns the derivative matrix of the node outputs with respect to the node inputs

        :param predictors: The input point we want to evaluate, must be a numpy matrix with one row
        :type predictors: np.array
        :return: The Jacobian matrix of the node, each columns represents and output, each row represents an input
                 and the values are the respective partial derivatives
        :rtype: np.array
        """
        grads = []
        outputs = self._model.output.shape[1]
        features = self._model.input.shape[1]

        for i in range(outputs):
            gradient = tf.gradients(self._model.output[0][i], self._model.input)[0]
            grads.append(tf.reshape(gradient, (1, features)))

        return tf.keras.backend.get_session().run(tf.concat(grads, axis=0), feed_dict={self._model.input: predictors})

    def save_model(self, address):
        self._model.save(address + self.name + '.h5')

    def load_model(self, address):
        self._model = load_model(address + self.name + '.h5')
