#!/bin/bash

if [ "$#" -ne 2 ]; then
   echo "Usage:   ./run.sh <parincipalName> <keytab_path>"
   echo "Example: ./run.sh phoenix_dev00 /home/phoenix_dev00/phoenix_dev00.keytab"
   exit 1
fi



if [ -z "$ES_NODES" ]; then
  export ES_NODES=10.132.181.136
fi
if [ -z "$ES_PORT" ]; then
  export ES_PORT=9200
fi

source ./reset-index.sh

echo "+++++++++++++++++++++ indexe reset"

PRINCIPAL_NAME=$1
KEYTAB_PATH=$2
MAIN=org.springframework.boot.loader.PropertiesLauncher
JAR=spark-es-indexer.jar

/usr/bin/spark2-submit --master yarn --deploy-mode cluster --name "Spark ES indexer" --class $MAIN \
        --principal $PRINCIPAL_NAME@applispfref.sipfref.local --keytab $KEYTAB_PATH \
        --files config/log4j.properties,config/application.properties,lib/elasticsearch-spark.jar \
        --num-executors 2 --executor-memory 3600m  --executor-cores 4  \
        --conf spark.memory.useLegacyMode=true --conf spark.shuffle.memoryFraction=0.8 --conf spark.storage.memoryFraction=0.05 \
        --conf spark.yarn.maxAppAttempts=1 \
        --conf spark.shuffle.service.enabled=false --conf spark.dynamicAllocation.enabled=false \
        --conf spark.driver.extraJavaOptions="-Dlog4j.configuration=log4j.properties" \
        --conf spark.executor.extraClassPath="elasticsearch-spark.jar" \
        --conf spark.yarn.appMasterEnv.ES_NODES=${ES_NODES} \
        --conf spark.yarn.appMasterEnv.ES_PORT=${ES_PORT} \
        $JAR
exit 0