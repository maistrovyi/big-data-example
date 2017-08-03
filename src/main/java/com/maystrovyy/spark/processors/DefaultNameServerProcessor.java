package com.maystrovyy.spark.processors;

import com.maystrovyy.configs.ApplicationConstants;
import com.maystrovyy.models.DomainStatus;
import com.maystrovyy.models.DomainType;
import com.maystrovyy.utils.formatters.NameServerFormatter;
import com.maystrovyy.utils.validators.DomainNameValidator;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import scala.Tuple2;

@Component
public class DefaultNameServerProcessor implements NameServerProcessor, NameServerFormatter {

    @Override
    public JavaRDD<Document> process(Dataset<String> dataset, DomainType type) {
        JavaPairRDD<String, Long> pairRdd = dataset
                .filter(nameServerFilter)
                .filter(DomainNameValidator::isDomainNameBeforeNSFilter)
                .map(mapToStringFunction(type), stringEncoder)
                .toJavaRDD()
                .mapToPair(string -> new Tuple2<>(string, 1L))
                .reduceByKey((a, b) -> a + b);

        return pairRdd.map(documentFunction);
    }

    private Encoder<String> stringEncoder = Encoders.STRING();

    private FilterFunction<String> nameServerFilter = string -> string.contains(ApplicationConstants.NS_FILTER);

    private MapFunction<String, String> mapToStringFunction(DomainType type) {
        return (MapFunction<String, String>) s -> {
            String[] array = s.split(ApplicationConstants.SPACE);
            return defaultFormatByType(array[2], type);
        };
    }

    private Function<Tuple2<String, Long>, Document> documentFunction = tuple ->
            new Document("_id", new ObjectId())
                    .append("name_server", tuple._1)
                    .append("domain", "")
                    .append("status", DomainStatus.UNKNOWN.getStatus())
                    .append("count", tuple._2);

}