package com.vdart.vdartcourses.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vdart.vdartcourses.collections.Quiz;

public interface QuizRepo extends MongoRepository<Quiz, ObjectId> {
    // Additional query methods can be defined here if needed

}
