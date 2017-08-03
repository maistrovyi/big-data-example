package com.maystrovyy.executors;

import com.google.common.net.InternetDomainName;
import com.maystrovyy.facades.DefaultDomainValidationFacade;
import com.maystrovyy.models.DomainType;
import com.maystrovyy.spark.io.NameServerRecordSparkOperationsService;
import com.maystrovyy.spark.processors.DefaultNameServerProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class NameServerExecutor {

    @Autowired
    private DefaultNameServerProcessor nameServerProcessor;

    @Autowired
    private NameServerRecordSparkOperationsService operationsService;

    @Autowired
    private DefaultDomainValidationFacade facade;

    @Async
    public void execute(Dataset<String> dataset) {
        JavaRDD<Document> rdd = nameServerProcessor.process(dataset, DomainType.DOT_NET);

        log.info("Execution started.");
        long before = System.nanoTime();
        JavaRDD<Document> newRdd = rdd.map(document -> {
                    String topDomain;
                    try {
                        topDomain = InternetDomainName
                                .from(document.getString("name_server")).topPrivateDomain().toString();
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        topDomain = "Unexpected";
                    }
                    log.info(" ");
                    return document.append("domain", topDomain);
                }
        ).map(facade.validateFunction());
        long after = System.nanoTime();
        log.info("Execution finished.");
        long time = TimeUnit.SECONDS.convert(after - before, TimeUnit.NANOSECONDS);
        log.info("Execution time : " + time + " seconds.");

        operationsService.save(newRdd);
    }

}