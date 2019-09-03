import pandas as pd
from keras import Sequential
from keras.layers import Dense
from sklearn.model_selection import train_test_split

if __name__ == '__main__':
    x = pd.read_csv("global_stats_inputs.csv")
    y = pd.read_csv("global_stats_outputs.csv")

    x = (x - x.mean()) / x.std()
    y = (y - y.mean()) / y.std()

    x_train, x_test, y_train, y_test  = train_test_split(x, y, train_size=0.75)

    number_of_features = x_train.columns.size
    number_of_outputs = y_train.columns.size
    number_of_layers = 5
    number_of_units = [64, 64, 64, 64, number_of_outputs]
    activations = ['relu', 'relu', 'relu', 'relu', 'linear']

    model = Sequential()
    for i in range(number_of_layers):
        model.add(Dense(number_of_units[i], activation=activations[i]))

    model.compile(optimizer='sgd', loss='mae', metrics=['mae'])

    model.fit(x_train.to_numpy(), y_train.to_numpy(), epochs=500)

    print(model.evaluate(x_test.to_numpy(), y_test.to_numpy()))
