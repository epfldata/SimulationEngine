from keras.models import Sequential
from keras.layers import *
import numpy as np
import os
import random

random.seed = 19

def getFolds(nFolds, nSamples):
    indices = list(range(nSamples))
    random.shuffle(indices)
    folds = []
    for i in range(nFolds):
        lower = int(nSamples * i / nFolds)
        upper = int(nSamples * (i + 1) / nFolds)
        test = indices[lower:upper]
        train = indices[:lower] + indices[upper:]
        folds.append((train, test))
    return folds

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

model.compile(optimizer='sgd', loss='mse', metrics=["mae"])

# shape = (samples, features)
train_X = np.loadtxt(open("target/scala-2.11/train_X.csv", "r"), delimiter=",", skiprows=1)
train_Y = np.loadtxt(open("target/scala-2.11/train_Y.csv", "r"), delimiter=",", skiprows=1).reshape((samples, timesteps, observables))
folds = getFolds(10, samples)
cvscores = {}
for name in model.metrics_names:
    cvscores[name] = []
for train, test in folds:
    model.fit(train_X[train], train_Y[train], epochs=150, batch_size=10, verbose=0)
    scores = model.evaluate(train_X[test], train_Y[test], verbose=0)
    if len(model.metrics_names) > 1:
        for i in range(len(model.metrics_names)):
            print("%s: %.2f%%" % (model.metrics_names[i], scores[i]))
            cvscores[model.metrics_names[i]].append(scores[i])
    else:
        print("%s: %.2f%%" % (model.metrics_names[0], scores))
        cvscores[model.metrics_names[0]].append(scores)
for name in model.metrics_names:
    print("%.2f%% (+/- %.2f%%)" % (np.mean(cvscores[name]), np.std(cvscores[name])))