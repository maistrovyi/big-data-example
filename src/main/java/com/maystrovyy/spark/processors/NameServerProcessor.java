package com.maystrovyy.spark.processors;

import com.maystrovyy.models.DomainType;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.bson.Document;

public interface NameServerProcessor {

    JavaRDD<Document> process(Dataset<String> rdd, DomainType type);

}