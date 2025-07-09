package com.vdart.vdartcourses.quiz;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepo extends MongoRepository<Quiz, ObjectId> {
    // Additional query methods can be defined here if needed

}
