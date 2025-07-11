package com.vdart.vdartcourses.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.ResourceNotFoundException;
import com.vdart.vdartcourses.question.Question;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }
    public Optional<Quiz> getQuizById(ObjectId id) {
        return quizRepo.findById(id);
    }
    public void deleteQuizById(ObjectId id) {
        quizRepo.deleteById(id);
    }
    public List<Quiz> getAllQuizzes() {
        return quizRepo.findAll();
    }
    public Question addQuestionToQuiz(ObjectId quizid, Question question) {

        Quiz quiz = quizRepo.findById(quizid)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizid));
        question.setQuizId((new ObjectId(quiz.getId())));
        if(quiz.getQuestions() == null) {
            quiz.setQuestions(new ArrayList<>());
        }
        question.setId(new ObjectId()); // Ensure the question has a new ID
        quiz.getQuestions().add(question);
        quizRepo.save(quiz);
        return question;
    }
   

}
