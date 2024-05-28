package com.test.demo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.lang.NonNull;

import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

    private static final int MONGO_DB_DEFAULT_PRIMARY_PORT = 27017;

    @Value("${mongodb.primary-port}")
    private Integer mongoDbPrimaryPort;

    @Value("${spring.data.mongodb.uri}")
    private String mongoDbURI;

    private final DemoWrapperWriteConverter demoWrapperWriteConverter;

    @Bean
    MongoTransactionManager transactionManager(@Qualifier("mongoDbFactory") MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

    @Override
    protected String getDatabaseName() {
        return "demo";
    }

    @Override
    public MongoCustomConversions customConversions() {
        final var customConverters = new ArrayList<Converter<?, ?>>();
        customConverters.add(demoWrapperWriteConverter);
        return new MongoCustomConversions(customConverters);
    }

    @NonNull
    @Override
    public MongoClient mongoClient() {
        String connectionString = mongoDbURI;

        //The default mongo port range is 27017 to 27019, only if the configured primary port is set and exceeds the
        //default port range, the default ports of the uri are replaced based on the new primary port
        if (mongoDbPrimaryPort != null && mongoDbPrimaryPort > MONGO_DB_DEFAULT_PRIMARY_PORT + 2) {
            for (int i = 0; i <= 2; i++) {
                connectionString =
                        connectionString.replace(
                                String.valueOf(MONGO_DB_DEFAULT_PRIMARY_PORT + i),
                                String.valueOf(mongoDbPrimaryPort + i)
                        );
            }
        }

        return createMongoClient(
                MongoClientSettings
                        .builder().applyConnectionString(new ConnectionString(connectionString)).build()
        );
    }

}
