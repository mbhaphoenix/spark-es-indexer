package com.rte.dsit.data.bdf.odessa.indexer.job;

import com.rte.dsit.data.bdf.odessa.indexer.conf.ApplicationArguments;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.elasticsearch.spark.sql.EsSparkSQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SparkJobRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SparkJobRunner.class);

    @Autowired
    private ApplicationArguments applicationArguments;

    public void run() throws Exception {

        LOGGER.debug("----------Application configuration properties: [{}]", applicationArguments);


        SparkSession spark = SparkSession
                .builder()
                .appName("Spark ES Indexer")
                .config("es.resource", applicationArguments.getIndexType())
                .config("es.nodes", applicationArguments.getEsNodes())
                .config("es.port", applicationArguments.getEsPort())
                .config("es.batch.size.bytes", "1mb")
                .config("es.batch.write.refresh", "false")
                .config("es.batch.size.entries", "10000")
                .config("es.index.auto.create", "true")
                .config("spark.sql.parquet.binaryAsString", "false")
                .getOrCreate();




        String sqlText = applicationArguments.getSqlQuery();

        LOGGER.debug("----------SQL query : [{}]", sqlText);

        Dataset dataset = spark.sql(sqlText).repartition(applicationArguments.getSplitsNumber());

        LOGGER.debug("----------Dataset to index count : {}", dataset.count());

        LOGGER.debug("----------About to index the result of the SQL query");

        EsSparkSQL.saveToEs(dataset, applicationArguments.getIndexType());

        LOGGER.debug("----------finished indexing the result of the SQL query");




    }

}
