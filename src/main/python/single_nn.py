import os

import pandas as pd
import sys
from keras import Sequential
from keras.layers import Dense
from keras.models import load_model
from sklearn.model_selection import train_test_split

if __name__ == '__main__':
    os.chdir('../../..')  # going to the root of the project

    if len(sys.argv) < 2:
        raise Exception("action required!")

    action = sys.argv[1]
    if action == 'train':
        x = pd.read_csv("supplementary/data/global_stat_input.csv")
        y = pd.read_csv("supplementary/data/global_stat_output.csv")

        x = (x - x.mean()) / x.std()
        y = (y - y.mean()) / y.std()

        x = x.fillna(1)
        y = y.fillna(1)

        x_train, x_test, y_train, y_test = train_test_split(x, y, train_size=0.75)

        number_of_features = x_train.columns.size
        number_of_outputs = y_train.columns.size
        number_of_layers = 10
        number_of_units = [64, 128, 256, 512, 512, 512, 256, 128, 64, number_of_outputs]
        activations = ['relu', 'relu', 'relu', 'relu', 'relu', 'relu', 'relu', 'relu', 'relu', 'linear']

        model = Sequential()
        for i in range(number_of_layers):
            model.add(Dense(number_of_units[i], activation=activations[i]))

        model.compile(optimizer='sgd', loss='mae', metrics=['mae'])

        model.fit(x_train.to_numpy(), y_train.to_numpy(), epochs=200)

        print(model.evaluate(x_test.to_numpy(), y_test.to_numpy()))

        if len(sys.argv) > 2 and sys.argv[2] == '--save':
            model.save('supplementary/models/single/single_nn.h5')

    elif action == 'evaluate':
        model = load_model('supplementary/models/single/single_nn.h5')

        x = pd.read_csv("supplementary/data/global_stat_input.csv")
        y = pd.read_csv("supplementary/data/global_stat_output.csv")

        x = (x - x.mean()) / x.std()
        y = (y - y.mean()) / y.std()

        print(model.evaluate(x.to_numpy(), y.to_numpy()))
