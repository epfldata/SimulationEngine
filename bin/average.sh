#!/bin/bash

input=$1
grep "Round" $input | grep "ms" | cut -d ' ' -f 10 > foo
awk '{ total += $1 } END { print total/NR }' foo
