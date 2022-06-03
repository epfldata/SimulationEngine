#!/bin/bash
# Bash script for performance tuning (Spark, Akka)
set -e

MEM=220000
exp=gameOfLife
repeats=3

tests=("${exp}TuneAkka" "${exp}TuneSpark" "${exp}TuneAkkaTimeseriesDeforestation" "${exp}TuneSparkTimeseriesDeforestation" "${exp}TuneAkkaTimeseries" "${exp}TuneSparkTimeseries")

mkdir -p log

for test in "${tests[@]}"
do
    i=1
    log_file=log/$test.csv
    touch $log_file

    while [ $i -le $repeats ]
    do
        echo Iteration: $i
        sbtCmd="project genExample; testOnly generated.example.test.${test}"
        sbt -mem $MEM "$sbtCmd" 
        cat ${exp}.csv >> $log_file
        rm ${exp}.csv
        ((i++))
        sleep 1
        echo 3 > /proc/sys/vm/drop_caches
    done
done