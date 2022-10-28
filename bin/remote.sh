#!/bin/bash

dist_conf="simulationEngine/Akka/src/main/resources/application.conf"
driver_worker_conf="simulationEngine/Akka/src/main/resources/driver-worker.conf"

changeIP() {
  ip_addr=`ip route | grep -oP "src \K[^ ]+"`
  seed_node=$1
  default_seed_node="SimsCluster@localhost:25251"
  actual_seed_node="SimsCluster@${seed_node}:25251"
  default_hostname='localhost'
  # Setup the seed node
  sed -i -e "s/${default_seed_node}/${actual_seed_node}/g" ${dist_conf}
  # Replace the localhost with actual ip
  sed -i -e "s/${default_hostname}/${ip_addr}/g" ${dist_conf}
} 

changeTotalMachines() {
  total_machines=$1 
  total_machine_keyword="total-machines = "
  echo ${driver_worker_file}
  sed -i -E "s/${total_machine_keyword}[0-9]+/${total_machine_keyword}${total_machines}/g" ${driver_worker_conf}
}

setupSSH() {
  echo "Setup ssh on $1"
  ssh-copy-id -i ~/.ssh/id_rsa.pub root@iccluster$1.iccluster.epfl.ch
}

onEachServer() {
  ssh root@iccluster$1.iccluster.epfl.ch "$(declare -f); $2"
}

copyTo() {
  echo "Copying script $2 to $1"
  scp $2 root@iccluster$1.iccluster.epfl.ch:/root/
}

ping() {
  echo "Hello, world!"
}