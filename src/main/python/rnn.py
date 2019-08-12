from keras.models import Sequential
from keras.layers import *
import numpy as np
import os

os.chdir('../../..')  # going to the root of the project

samples = 10
timesteps = 300
features = 28
observables = 9


model = Sequential([
    Dense(32, input_dim=features),
    RepeatVector(timesteps),
    SimpleRNN(32, return_sequences=True),
    TimeDistributed(Dense(observables))])

model.compile(optimizer='sgd', loss='mse')

# shape = (samples, features)
train_X = np.loadtxt(open("target/scala-2.11/train_X.csv", "r"), delimiter=",", skiprows=1)
train_Y = np.loadtxt(open("target/scala-2.11/train_Y.csv", "r"), delimiter=",", skiprows=1).reshape((samples, timesteps, observables))
model.fit(train_X, train_Y)