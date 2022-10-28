#!/bin/bash

set -e
bash bin/init.sh
sbt clean
sbt "project akka; test"
sbt clean
sbt "project base; test"
sbt clean
sbt "project spark; test"