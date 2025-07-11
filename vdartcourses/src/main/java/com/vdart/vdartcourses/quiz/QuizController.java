package com.vdart.vdartcourses.quiz;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdart.vdartcourses.ResourceNotFoundException;
import com.vdart.vdartcourses.question.Question;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // Get all quizzes
    // Get quiz by id
    // Add a new quiz
    // Delete a quiz by id
    // Update a quiz by id

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable ObjectId id) {
        return quizService.getQuizById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
    }

    @PostMapping("/post")
    public Quiz saveQuiz(@RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }
    @GetMapping("/delete/{id}")
    public void deleteQuizById(@PathVariable ObjectId id) {
        quizService.deleteQuizById(id);
    }
    @PostMapping("{quizid}/question/add")
    public Question addQuestionToQuiz(@PathVariable ObjectId quizid, @RequestBody Question question) {
        return quizService.addQuestionToQuiz(quizid, question);
    }
    
    
    
        
}
