package com.vdart.vdartcourses.quiz;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.ResourceNotFoundException;

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
    public Optional<Quiz> updateQuizById(ObjectId id, Quiz quiz) {
        Quiz existingQuiz = quizRepo.findById(id).orElseThrow(() ->
            new ResourceNotFoundException("Quiz not found with id: " + id));
        
            existingQuiz.setTitle(quiz.get());
            existingQuiz.setDescription(quiz.getDescription());
            existingQuiz.setQuestions(quiz.getQuestions());
            return Optional.of(quizRepo.save(existingQuiz));
        }
        return Optional.empty();
    }

}
