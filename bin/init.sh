#!/bin/bash

set -e
DIRS=("gen-core/src/main/" "generated/src/main/")
CMDS=("project core; test" "project example; runAll")

idx=0
for DIR in "${DIRS[@]}"
do
    echo "Check if $DIR is empty"
    if [ "$(ls -A $DIR)" ]; then
        echo "Remove existing files in $DIR"
        rm -rf $DIR
    fi
    echo "Generating new files for $DIR"
    sbt "${CMDS[$idx]}"
    idx=$(( idx + 1 ))
done