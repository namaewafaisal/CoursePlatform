package com.vdart.vdartcourses.question;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepo extends MongoRepository<Question, ObjectId> {

    List<Question> findByQuizId(String quizId);
    // Additional query methods can be defined here if needed

}
