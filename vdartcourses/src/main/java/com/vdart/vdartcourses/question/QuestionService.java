package com.vdart.vdartcourses.question;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public Question saveQuestion(Question question) {
        
        return questionRepo.save(question);

    }
    public List<Question> getQuestionsByQuizId(String quizId) {
        return questionRepo.findByQuizId(quizId);
    }
}
