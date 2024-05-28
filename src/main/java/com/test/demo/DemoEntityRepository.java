package com.test.demo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoEntityRepository extends MongoRepository<DemoEntity, ObjectId> {
}
