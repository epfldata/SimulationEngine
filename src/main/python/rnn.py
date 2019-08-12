from keras.models import Sequential
from keras.layers import *

samples = 40
timesteps = 1000
features = 28
observables = 9


model = Sequential([
    Dense(32, input_dim=features),
    RepeatVector(timesteps),
    SimpleRNN(32, return_sequences=True),
    TimeDistributed(Dense(observables))])

model.compile(optimizer='sgd', loss='mse')
# next lines must be read from a csv
# read train_X
# read train_Y
train_X = np.random.rand(samples, features)
train_Y = np.random.rand(samples, timesteps, observables)

model.fit(train_X, train_Y)


