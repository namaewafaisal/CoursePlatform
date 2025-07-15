package com.vdart.vdartcourses.quiz;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepo extends MongoRepository<Quiz, ObjectId> {

    Optional<Quiz> findByCourseId(ObjectId courseId);
    // Additional query methods can be defined here if needed

}
