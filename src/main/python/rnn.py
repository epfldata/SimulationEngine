import tensorflow as tf
from keras.models import Sequential
from keras.layers import *
from keras.losses import mean_absolute_error
import numpy as np
import os
import random

random.seed = 19
os.chdir('../../..')  # going to the root of the project

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

def createSession():
    # See https://www.tensorflow.org/tutorials/using_gpu#allowing_gpu_memory_growth
    config = tf.ConfigProto()
    config.gpu_options.allow_growth = True
    sess = tf.Session(config=config)
    try:
        sess.run(tf.global_variables_initializer())
        sess.run(tf.local_variables_initializer())
    except tf.errors.InvalidArgumentError:
        print(
            '\n\nThis error most likely means that this notebook is not '
            'configured to use a GPU.  Change this in Notebook Settings via the '
            'command palette (cmd/ctrl-shift-P) or the Edit menu.\n\n')
        raise
    return sess

samples = 100
timesteps = 1000
features = 28
observables = 9

deviceName = "/cpu:0" if tf.test.gpu_device_name() == "" else tf.test.gpu_device_name()
print("use device", deviceName)
with tf.device(deviceName):
    # with tf.device('/device:GPU:0'):
    input = tf.placeholder(shape=[None, features], dtype=tf.float32)
    labels = tf.placeholder(shape=[None, timesteps, observables], dtype=tf.float32)
    model = Sequential([
        Dense(32, input_dim=features),
        RepeatVector(timesteps),
        SimpleRNN(32, return_sequences=True),
        TimeDistributed(Dense(observables))])
    preds = model(input)
    loss = mean_absolute_error(labels, preds)
    train_step = tf.train.GradientDescentOptimizer(0.5).minimize(loss)
    metrics = mean_absolute_error(labels, preds)

# shape = (samples, features)
train_X = np.loadtxt(open("target/scala-2.11/train_X.csv", "r"), delimiter=",", skiprows=1)
train_Y = np.loadtxt(open("target/scala-2.11/train_Y.csv", "r"), delimiter=",", skiprows=1).reshape((samples, timesteps, observables))
folds = getFolds(10, samples)
cvscores = []
for train, test in folds:
    with createSession() as sess:
        sess.run(train_step, feed_dict={input: train_X[train], labels: train_Y[train]})
        mae = sess.run(metrics, feed_dict={input: train_X[test], labels: train_Y[test]})
        print("Mean absolute error: %.2f%%" % np.mean(mae))
        cvscores.append(np.mean(mae))
print("%.2f%% (+/- %.2f%%)" % (np.mean(cvscores), np.std(cvscores)))