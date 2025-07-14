package com.vdart.vdartcourses.quiz;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.ResourceNotFoundException;
import com.vdart.vdartcourses.course.Course;
import com.vdart.vdartcourses.course.CourseService;
import com.vdart.vdartcourses.question.Question;
import com.vdart.vdartcourses.question.QuestionRepo;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private CourseService courseService;

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
    public void addQuestionToQuiz(ObjectId quizId, Question question) {
        question.setQuizId(quizId);
        questionRepo.save(question);
    }
    public Optional<Quiz> attendQuiz(ObjectId courseId) {
        Course course = courseService.getCourseById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        return quizRepo.findById(course.getFinalQuizId());
        //         .map(Quiz::getQuestions)
        //         .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + course.getFinalQuizId()));
        // return questions;
    }
    // public Quiz updateQuiz(ObjectId quiz, Quiz updatedQuiz) {
    //     Quiz existingQuiz = quizRepo.findById(quiz)
    //             .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quiz));
    //     if (updatedQuiz.() != null) {
    //         existingQuiz.setTitle(updatedQuiz.getTitle());
    //     }
    //     existingQuiz.setDescription(updatedQuiz.getDescription());
    //     existingQuiz.setQuestions(updatedQuiz.getQuestions());
    //     return quizRepo.save(existingQuiz);
    // }
   

}
