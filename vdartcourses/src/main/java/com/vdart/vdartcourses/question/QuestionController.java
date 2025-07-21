package com.vdart.vdartcourses.question;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/questions")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;

    @GetMapping("/quizid/{quizId}")
    @PreAuthorize("isAuthenticated()")
    public List<Question> getQuestionsByQuizId(@PathVariable ObjectId quizId) {
        return questionService.getQuestionsByQuizId(quizId);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN')")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    

    // Additional methods for updating and deleting questions can be added here
}
