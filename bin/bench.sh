#!/bin/bash

set -e

MEM=220000
repeats=3

exps=('epidemicERM' 'epidemicSBM' 'transportation1kStaticTestAkka' 'stockMarketStaticTestAkka' 'transportation5kStaticTestAkka' 'transportation10kStaticTestAkka' 'gameOfLifeStaticTestAkka')

mkdir -p log

sbt "project example; runAll"

i=1
while [ $i -le $repeats ]
do
    for test in "${exps[@]}"
    do
        log_file=log/${test}.csv
        touch $log_file
        echo Iteration: $i
        sbtCmd="project genExample; testOnly generated.example.test.${test}"
        echo $sbtCmd
        sbt -mem $MEM "$sbtCmd"
        cat ${test}.csv >> $log_file
        rm ${test}.csv
        sleep 1
        echo 3 > /proc/sys/vm/drop_caches
    done
    ((i++))
done