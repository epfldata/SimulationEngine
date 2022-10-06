#!/bin/bash
# Bash script for performance tuning (Spark, Akka)
set -e

MEM=100000

modes=("1" "2" "3" "4" "5")
widths=("100" "1000")
height="1000"
totalRounds="200"

mkdir -p log

for width in "${widths[@]}"
do
    for mode in "${modes[@]}"
    do
        log_file=log/"gol_perf_w${width}_h${height}_m$mode"
        touch $log_file
        sbtCmd="project akka; test:runMain simulation.akka.test.gameOfLifeBench $width $height $totalRounds $mode Standalone 25251"
        echo $sbtCmd
        perf stat -a -d sbt -mem $MEM "$sbtCmd" > $log_file 2>&1
        sleep 1
        sudo sh -c 'echo 3 >/proc/sys/vm/drop_caches'
    done
done
    
