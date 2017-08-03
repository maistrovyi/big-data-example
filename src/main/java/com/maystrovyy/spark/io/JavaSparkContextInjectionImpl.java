package com.maystrovyy.spark.io;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JavaSparkContextInjectionImpl implements JavaSparkContextInjection {

    private static final long serialVersionUID = 4419874549049945894L;

    @Autowired
    private SparkSession sparkSession;

    @Override
    public JavaSparkContext getJavaSparkContext() {
        return JavaSparkContext.fromSparkContext(sparkSession.sparkContext());
    }

}