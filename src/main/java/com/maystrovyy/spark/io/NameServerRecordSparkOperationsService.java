package com.maystrovyy.spark.io;

import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.WriteConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.JavaRDD;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NameServerRecordSparkOperationsService extends JavaSparkContextInjectionImpl {

    private static final long serialVersionUID = 6056752113205783080L;

    public void save(JavaRDD<Document> rdd) {
        WriteConfig writeConfig = WriteConfig.create(getJavaSparkContext()).withOption("collection", "test");
        MongoSpark.save(rdd, writeConfig);
        log.info("Successfully saved!");
    }

}