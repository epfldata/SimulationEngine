#!/bin/bash

set -e

MEM=100000

components=("10" "20" "30" "40" "50" "60" "70" "80" "90" "100")
width="100"
height="1000"
totalRounds="200"

mkdir -p log

mode="1"

for worker in "${components[@]}"
do
    python3 bin/akkaConfParser.py "driver-worker.conf" workers-per-machine $worker
    log_file=log/"components_${worker}"
    touch $log_file
    sbtCmd="project akka; test:runMain simulation.akka.test.gameOfLifeBench $width $height $totalRounds $mode Standalone 25251"
    echo $sbtCmd
    sbt -mem $MEM "$sbtCmd" > $log_file 2>&1
    sleep 1
    sudo sh -c 'echo 3 >/proc/sys/vm/drop_caches'
done
    