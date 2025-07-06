package com.vdart.vdartcourses.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vdart.vdartcourses.collections.Question;

public interface QuestionRepo extends MongoRepository<Question, ObjectId> {
    // Additional query methods can be defined here if needed

}
