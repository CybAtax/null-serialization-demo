package com.test.demo;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NullSerializationTest {

    @Autowired
    private DemoEntityRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void nullValueSerialization() {
        final var demoEntity = new DemoEntity();
        demoEntity.setValue(new DemoWrapper(DemoWrapper.UNDEFINED_PRIMITIVE));
        final var savedEntity = repository.save(demoEntity);

        final var doc = mongoTemplate.findById(savedEntity.getId(), Document.class, "demoentity");
        assertNotNull(doc);
        assertFalse(doc.containsKey("value"));
    }

}
