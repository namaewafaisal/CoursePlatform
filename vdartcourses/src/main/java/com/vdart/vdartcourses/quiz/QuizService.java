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
import com.vdart.vdartcourses.question.QuestionService;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;
    
    @Autowired
    private CourseService courseService;

    @Autowired
    private QuestionService questionService;

    public Quiz saveQuizForCourse(String courseId) {
        Quiz quiz = new Quiz();
        quiz.setCourseId(new ObjectId(courseId));
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
    public Question addQuestionToQuiz(ObjectId quizId, Question question) {
        Quiz quiz = quizRepo.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId));
        question.setQuizId(quizId);
        questionService.saveQuestion(question);
        return question;
    }
    public List<Question> attendQuiz(ObjectId courseId) {
        Quiz quiz = quizRepo.findByCourseId(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found for course id: " + courseId));
        return questionService.getQuestionsByQuizId(new ObjectId(quiz.getId()));

    }
    public Quiz saveQuiz(Quiz quiz) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveQuiz'");
    }
    public Optional<Quiz> getQuizzesByCourseId(ObjectId courseId) {
        return quizRepo.findByCourseId(courseId);
    }

}
