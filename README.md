# economic_simulations
This fork contains the neural network and bayesian optimization implementations. The neural network is supposed to learn the behavior of the simulation, therefore be able to produce the simulation results much faster than the actual simulation. With it's high speed comparing to the actual simulation, it can be used to generated lots of outputs from different initial conditions. This data can later be used to analyze the correlation and possibly the causality between the agents. 

## Architecture and Hierarchy of the Network
Here we use two abstraction levels to model the simulation.
1. Environment Level: In the higher level of abstraction each type of agent from the simulation (e.g. Person, Farm, ...) is represented by an instance of the class Agent. From now on by agent we mean an instance of the class Agent, in other words an agent type from the simulation. An environment (instance of class Environment) keeps the information of all the agents in the world as well as the connections between them. A connection from agent A to agent B means that in the simulation agent B relies on information from agent A. **We also assume by default that each agent has a connection to itslef.** These connections form a graph which is used in the lower level of abstraction.

2. Graph Level: In the lower level of abstraction each type of agent is represented by a node in a graph. There is an edge from node A to node B if and only if there is a connection from agent A to agent B in the environment. These nodes contain the actual neural network used for each agent. 

The environment level is merely a wrapper to make working with the graph level easier for the end user, with no requirement to work with the underlying neural networks.

## Getting Started
### Configuring and Compiling the Environment
For using the neural network abilities, first of all you have to define the high level environment. You can do it by defining an environemnt instance 

```python
env = Environment()
```
You can add new agents to the environment using `env.register_agents(*agents)` and connections using `env.register_connections(from_agent, *to_agents)`.
After setting up the configuration of the environment using the two methods, you can compile the environment. This makes the environment ready for training.
```python
env.compile()
```
It's important to compile the environment before using it for training, otherwise the low level graph and nodes from agents won't be created.

### Training
There are two different approaches for training. 
#### Solo Training
In the first approach (solo training) each agent is trained individually. This means that for each agent, we try to update its neural network weights according to the loss function computed from its outputs only. However, we still need the whole data of all agents to train each agent individually, since it may have connections from other agents and depend on their data.
You can use this simple method to run the solo training
```python
env.solo_training(train_input, train_output, training_hyper_parameters=None)
```
Both `train_input` and `train_output` have to be dictionaries, where keys are the agents. For `train_input` the values are also dictionaries, where the keys are either `'constants'` or `'states'`, which are the two different types of parameters an agent can have, and the values are pandas dataframes. For `train_output` the values are simply pandas dataframes containing only the `'states'` parameters. If you want to manually set hyper parameters for training, the `training_hyper_parameters` should be a dictionary, where its keys are agents and the values are dictionaries containing the values for hyper parameters. The possible hyper parameters to be passed are `batch_size` and `epochs`.

#### Group Training
In the second approach (group training) all agents are trained together. This means that we try to update all weights of different networks simultaneously according to the loss function computed from the aggregated outputs.
You can use this method to run the group training
```python
env.group_train(train_input, train_output, aggregator, epochs=100)
```
`train_input` and `train_output` must have the exact same structure as in the solo training. `aggregator` is an instance of the abstract base class `Aggregator`, which its `aggregate` method must be able to aggregate tensorflow tensors accross different agents into one tensor. This is used for computing the aggregated outputs from individual outputs of agents, thus computing the loss function for training.

### Testing
Testing can be done for evaluating the precision of the training process, as well as comparing this model with other possible models (like using a single feed forward network to predict everything). Just like training, there are two different methods for testing the model.
Solo testing for testing the output of each agent against its own ground truth can be called using the following method. The metric used for testing here is mean absolute error.
```python
env.solo_test(test_input, test_output)
```
Group testing for testing the aggregated output of agents against the aggregated ground truth can be called using the following method. The metric used for teseting here is mean squared error.
```python
env.group_test(test_input, test_output)
```
The structure of `test_input` and `test_output` is the same as `train_input` and `train_output`.

### Saving and Loading Models

## Using the Analysis Tools
### Input Learning
### Correlation Matrix
### Derivative Matrix
