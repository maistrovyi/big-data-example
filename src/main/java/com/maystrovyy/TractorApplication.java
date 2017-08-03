package com.maystrovyy;

import com.maystrovyy.configs.ApplicationConstants;
import com.maystrovyy.executors.NameServerExecutor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication
public class TractorApplication implements CommandLineRunner {

    @Autowired
    private SparkSession sparkSession;

    @Autowired
    private NameServerExecutor nameServerExecutor;

    @Autowired
    private MongoOperations mongoOperations;

    public static void main(String[] args) {
        SpringApplication.run(TractorApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        mongoOperations.dropCollection("test");
        mongoOperations.createCollection("test");

        Dataset<String> dataset = sparkSession.read().textFile(ApplicationConstants.DATA_PATH);
        nameServerExecutor.execute(dataset);
    }

}