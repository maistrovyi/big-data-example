package com.maystrovyy.configs;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@EnableMongoAuditing
@SpringBootConfiguration
public class MongoConfiguration extends AbstractMongoConfiguration {

    @Value(value = "${spring.data.mongodb.name}")
    private String mongoName;

    @Value(value = "${spring.data.mongodb.net.bindIp}")
    private String mongoHost;

    @Value(value = "${spring.data.mongodb.net.port}")
    private int mongoPort;

    @Override
    protected String getDatabaseName() {
        return mongoName;
    }

    @Bean
    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(mongoHost, mongoPort);
    }

    @Bean(name = "mongoOperations")
    public MongoOperations mongoOperations() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }

}