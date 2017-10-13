package com.rte.dsit.data.bdf.odessa.indexer.job;

import com.rte.dsit.data.bdf.odessa.indexer.conf.ApplicationArguments;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.elasticsearch.spark.sql.EsSparkSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.spark.sql.api.java.*;
import org.elasticsearch.spark.sql.api.java.JavaEsSparkSQL;




@Component
public class SparkJobRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SparkJobRunner.class);

    @Autowired
    private ApplicationArguments applicationArguments;

    public void run() throws Exception {

        SparkSession spark = SparkSession
                .builder()
                .appName("Spark ES Indexer")
                .config("es.resource", applicationArguments.getIndex() + "/" + applicationArguments.getType())
                .config("spark.es.resource", applicationArguments.getIndex() + "/" + applicationArguments.getType())
                .config("es.nodes", "10.132.181.136")
                .config("es.port", "9200")
                .config("es.index.auto.create", "true")
                .config("spark.yarn.maxAppAttempts", 1)
                .config("spark.sql.parquet.binaryAsString", "true")
                .getOrCreate();

        LOGGER.debug("----------Application configuration properties: [{}]", applicationArguments);

        //TODO number of retries 1



        /*final String insertPartitionSqlString = "INSERT INTO TABLE " + applicationArguments.getTargetDbName() + "." + applicationArguments.getTargetTableName() +
                " PARTITION (" + applicationArguments.getPartitionColumns() + ")" +
                " SELECT * FROM " + applicationArguments.getSrcDbName() + "." + applicationArguments.getSrcTableName() +
                " WHERE " + applicationArguments.getPartitionFilterExpression() +
                " DISTRIBUTE BY " + applicationArguments.getPartitionColumns();*/

        spark.conf().set("spark.sql.parquet.binaryAsString", "true");

        String sqlText = "select * FROM " +
                applicationArguments.getDbName() + "." + applicationArguments.getTableName() +
                " WHERE valueyear = '2015'" +
                " AND valuemonth='09' " +
                " AND valueday in ('01', '02', '03', '04', '05', '06', '07', '08', '09', '10')";

        LOGGER.debug("----------SQL query : [{}]", sqlText);

        Dataset dataset = spark.sql(sqlText);

        LOGGER.debug("----------Dataset to index count : {}", dataset.count());

        LOGGER.debug("----------About to index the table {}.{}", applicationArguments.getDbName(), applicationArguments.getTableName());

        EsSparkSQL.saveToEs(dataset, applicationArguments.getIndex() + "/" + applicationArguments.getType());

        LOGGER.debug("----------finished indexing the table {}.{}", applicationArguments.getDbName(),
                applicationArguments.getTableName());

//        spark.sql(insertPartitionSqlString);


    }

}
