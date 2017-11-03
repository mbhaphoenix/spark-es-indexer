#!/bin/bash
cd ~

rm -rf spark-es-indexer-0.1-SNAPSHOT/
tar -xzvf spark-es-indexer-0.1-SNAPSHOT-dist.tar.gz
cd spark-es-indexer-0.1-SNAPSHOT
./run.sh phoenix_dev00 /home/phoenix_dev00/phoenix_dev00.keytab
cd

exit 0
