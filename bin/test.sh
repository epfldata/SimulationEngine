#!/bin/bash

set -e
sbt clean
sbt "project akka; test"
sbt "project base; test"
