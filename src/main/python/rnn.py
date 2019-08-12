from keras.models import Sequential
from keras.layers import *
import pandas as pd
import os

os.chdir('../../..')  # going to the root of the project

samples = 10
timesteps = 1000
features = 28
observables = 9


model = Sequential([
    Dense(32, input_dim=features),
    RepeatVector(timesteps),
    SimpleRNN(32, return_sequences=True),
    TimeDistributed(Dense(observables))])

model.compile(optimizer='sgd', loss='mse')

data = pd.read_csv("target/scala-2.11/train.csv")
train_X = data[['capitalMu', 'capitalSigma', 'factoryItersMu',
       'factoryItersSigma', 'factorySalaryMu', 'factorySalarySigma',
       'farmItersMu', 'farmItersSigma', 'farmSalaryMu', 'farmSalarySigma',
       'femaleBonusSalMu', 'femaleBonusSalSigma', 'femaleEduMu',
       'femaleEduSigma', 'femaleFoodMu', 'femaleFoodSigma', 'genderMu',
       'genderSigma', 'maleBonusSalMu', 'maleBonusSalSigma', 'maleEduMu',
       'maleEduSigma', 'maleFoodMu', 'maleFoodSigma', 'millItersMu',
       'millItersSigma', 'millSalaryMu', 'millSalarySigma']].drop_duplicates().to_numpy() #let's hope it preserves order!!

train_Y = data[['CapitalAvg', 'UnemploymentRate', 'MillEmploymentRate', 'FarmEmploymentRate',
                'WheatAvgPrice', 'FlourAvgPrice', 'MovieAvgPrice', 'BeefAvgPrice',
                'BurgerAvgPrice']].to_numpy().reshape(samples, timesteps, observables)


model.fit(train_X, train_Y)

