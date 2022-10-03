#!/bin/bash

set -e

sbt clean
sbt "project core; test"
sbt "project example; runAll"
sbt "project base; test"
