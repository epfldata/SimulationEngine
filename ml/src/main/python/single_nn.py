import os

import numpy as np
import pandas as pd
import sys
import tensorflow as tf
from sklearn.model_selection import KFold
from tensorflow.keras import Sequential
from tensorflow.keras.layers import Dense


def read_datasets(dir):
    x = pd.read_csv(dir + "global_stat_input.csv")
    y = pd.read_csv(dir + "global_stat_output.csv")

    x = (x - x.mean()) / x.std()
    y = (y - y.mean()) / y.std()

    x = x.fillna(1)
    y = y.fillna(1)
    return x, y


def evaluate(model, eval_instance_name):
    x_test, y_test = read_datasets("supplementary/data/" + eval_instance_name + "/")
    print(model.evaluate(x_test.to_numpy(), y_test.to_numpy()))


def k_cross_fold_validation(x_train, number_of_outputs, number_of_layers, number_of_units, activations):
    x_train = x_train.to_numpy()
    i = sys.argv.index("-k")
    k = int(sys.argv[i + 1])
    n = x_train.shape[0]
    if k < 2 or k > n:
        print("k has to be between 2 and n")
        exit(1)

    predictions = np.empty(shape=(0, number_of_outputs))
    fold_counter = 1
    for train_index, test_index in KFold(k, shuffle=True, random_state=19).split(x_train):
        print("Fold", fold_counter)
        x_train_fold, x_test_fold = x_train[train_index], x_train[test_index]
        y_train_fold = x_train[train_index]

        model = build_model(number_of_layers, number_of_units, activations)
        model.fit(x_train_fold, y_train_fold, epochs=150)

        preds = model.predict(x_test_fold)
        predictions = np.concatenate((predictions, preds), axis=0)
        fold_counter += 1
    mae = tf.keras.metrics.mean_absolute_error(x_train, predictions)
    mse = tf.keras.metrics.mean_squared_error(x_train, predictions)
    print("cross validation errors")
    print("mae", tf.keras.backend.eval(mae).mean())
    print("mse", tf.keras.backend.eval(mse).mean())


def build_model(number_of_layers, number_of_units, activations):
    model = Sequential()
    for i in range(number_of_layers):
        model.add(Dense(number_of_units[i], activation=activations[i]))
    model.compile(optimizer='sgd', loss='mse', metrics=['mae'])
    return model


if __name__ == '__main__':
    os.chdir('../../..')  # going to the root of the project

    if len(sys.argv) < 2:
        raise Exception("action required!")

    action = sys.argv[1]
    train_instance_name = "5000-40-5"
    eval_instance_name = "500-5-5"
    if action == 'train':
        x_train, y_train = read_datasets("supplementary/data/" + train_instance_name + "/")

        number_of_features = x_train.columns.size
        number_of_outputs = y_train.columns.size
        number_of_layers = 4
        number_of_units = [16, 32, 16, number_of_outputs]
        activations = ['relu', 'relu', 'relu', 'linear']

        if "-k" in sys.argv:
            k_cross_fold_validation(x_train, number_of_outputs, number_of_layers, number_of_units, activations)

        else:
            model = build_model(number_of_layers, number_of_units, activations)
            model.fit(x_train.to_numpy(), y_train.to_numpy(), epochs=150)
            evaluate(model, eval_instance_name)
            if '--save' in sys.argv:
                model.save('supplementary/models/' + train_instance_name + '/single_nn.h5')

    elif action == 'evaluate':
        model = tf.keras.models.load_model('supplementary/models/' + train_instance_name + '/single_nn.h5')
        evaluate(model, eval_instance_name)
