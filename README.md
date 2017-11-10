#Tuning nodes for bulk indexing 

thread_pool.bulk.size: cores number (max cores number +1 )
thread_pool.bulk.queue_size: 300

##Setting an index

```javascript
{
 "settings": {
   "number_of_shards": 10,
   "number_of_replicas": 0,
   "refresh_interval": "60s",
   "translog": {
     "durability": "async",
     "sync_interval": "60s"
   }
 }
}
```

##Setting a type
```javascript
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
   }
 }
}
```




 
