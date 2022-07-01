#!/bin/bash
seed_node=$1

ip_addr=`ip route | grep -oP "src \K[^ ]+"`

default_seed_node="SimsCluster@localhost:25251"
actual_seed_node="SimsCluster@${seed_node}:25251"

default_hostname='localhost'

default_role='Dispatcher'
sims_role='Sims'

conf_file="simulationEngine/generated/src/main/resources/application.conf"

log4j_file="simulationEngine/generated/src/main/resources/logback.xml"

cp simulationEngine/generated/src/main/resources/dist.conf "${conf_file}"

# Setup the seed node
sed -i -e "s/${default_seed_node}/${actual_seed_node}/g" ${conf_file}

# Replace the localhost with actual ip
sed -i -e "s/${default_hostname}/${ip_addr}/g" ${conf_file}

if [ "${ip_addr}" != "${seed_node}" ]; then
    # Change the role of non-seed node to Sim
    sed -i -e "s/${default_role}/${sims_role}/g" ${conf_file}
else
    # Set the log level of dispatcher to warn to show progress
    sed -i -e "s/error/warn/g" ${log4j_file}
fi