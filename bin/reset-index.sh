#!/bin/bash
set -e
set -x

if [ -z "$ES_NODES" ] || [ -z "$ES_PORT" ]; then
  exit 10
fi

curl -XDELETE "$ES_NODES:$ES_PORT/spark_20171025"

curl -XPUT "$ES_NODES:$ES_PORT/spark_20171025?pretty" -H 'Content-Type: application/json' -d'
{
  "settings": {
    "number_of_shards": 8,
    "number_of_replicas": 0,
    "refresh_interval": "60s",
    "translog": {
      "durability": "async",
      "sync_interval": "60s"
    }
  }
}
'
curl -XPUT "$ES_NODES:$ES_PORT/spark_20171025/_mapping/pf_parquet_09_10days?pretty" -H 'Content-Type: application/json' -d'
{
  "_all":       { "enabled": false  },
  "dynamic": false,
  "properties": {
    "countrya": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "countryb": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "metric": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "name": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "prodtype": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "regiona": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "regionb": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "type": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "value": {
      "type": "float"
    },
    "valuedate": {
      "type": "long"
    },
    "valueday": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "valuemonth": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "valueyear": {
      "type": "text",
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    }
  }
}

GET /_cluster/settings

PUT /spark_20171025/_mapping/pf_parquet_09_10days
{
  "_all":       { "enabled": false  },
  "dynamic": false,
  "properties": {
    "countrya": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "countryb": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "metric": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "name": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "prodtype": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "regiona": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "regionb": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "type": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "value": {
      "type": "float"
    },
    "valuedate": {
      "type": "long"
    },
    "valueday": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "valuemonth": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    },
    "valueyear": {
      "type": "text",
      "norms": false,
      "fields": {
        "keyword": {
          "type": "keyword",
          "ignore_above": 64
        }
      }
    }
  }
}
'


set +x