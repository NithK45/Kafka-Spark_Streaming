# Kafka-Spark_Streaming
Scala program for Spark streaming from Kafka Topics

This is a scala program does the spark streaming from a Kafka topic using Kafka utils createstream api. It collects data every 5 seconds, each RDD from returned from kafka utils is saved as JSON files in HDFS location. These can be go to Hive external tables to view the data in tables.

If this data is to feed to visualization tools, hive hcatalog jar file should be initiated manually for every session, and some visualization tools like Power Bi do not have feature to run "initial SQL" which Tableau has, after establishing connection to Hive through ODBC connectors. 

To initialize custom/standard jar files when a session is open, follow the instructions given in the below cloudera link.

https://www.cloudera.com/documentation/enterprise/5-6-x/topics/cm_mc_hive_udf.html#concept_nc3_mms_lr 
