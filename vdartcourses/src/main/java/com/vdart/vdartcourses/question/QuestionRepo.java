package com.vdart.vdartcourses.question;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepo extends MongoRepository<Question, ObjectId> {
    // Additional query methods can be defined here if needed

}
