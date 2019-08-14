from keras.models import Sequential
from keras.layers import *
import pandas as pd
import os

os.chdir('../../..')  # going to the root of the project

samples = 100
timesteps = 1000
features = 28
observables = ["CapitalAvg", "UnemploymentRate", "MillEmploymentRate", "FarmEmploymentRate", "WheatAvgPrice",  "FlourAvgPrice", "MovieAvgPrice","BeefAvgPrice"]


model = Sequential([
            Dense(32, input_dim=features),
            RepeatVector(timesteps),
            SimpleRNN(32, return_sequences=True),
            TimeDistributed(Dense(len(observables)))])

model.compile(optimizer='rmsprop', loss='mae')

x = pd.read_csv("target/scala-2.11/train_X.csv").to_numpy()
y = pd.read_csv("target/scala-2.11/train_Y.csv")[observables].to_numpy().reshape(samples, timesteps, len(observables))

model.fit(x, y, epochs=10)

print(model.evaluate(x, y))
