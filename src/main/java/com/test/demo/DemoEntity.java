package com.test.demo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "demoentity")
public class DemoEntity {

    @Id
    @Setter(AccessLevel.NONE)
    private ObjectId id;

    private DemoWrapper value;

}
