package com.maystrovyy.configs;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class SparkConfiguration {

    @Bean
    public SparkConf sparkConf() {
        return new SparkConf()
                .setAppName("Tractor")
                .setMaster("local[*]")
                .set("spark.app.id", "Tractor")
                .set("spark.default.parallelism", "4")
                .set("spark.executor.instances", "4")
                .set("spark.executor.cores", "4")
                .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
                .set("spark.kryo.registrationRequired", "false")
                .set("spark.mongodb.input.uri", "mongodb://localhost:32768/tractor.test")
                .set("spark.mongodb.output.uri", "mongodb://localhost:32768/tractor.test");
    }

    @Bean
    public SparkContext sparkContext() {
        return new SparkContext(sparkConf());
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(sparkContext());
    }

    @Bean
    public SparkSession sparkSession() {
        return new SparkSession(sparkContext());
    }

    @Bean
    public SQLContext sqlContext() {
        return new SQLContext(sparkSession());
    }

}