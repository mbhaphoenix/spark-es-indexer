#!/bin/bash
inotifywait -q -m -e close_write spark-es-indexer-0.1-SNAPSHOT-dist.tar.gz |
while read events; do
  ./go.sh
done
