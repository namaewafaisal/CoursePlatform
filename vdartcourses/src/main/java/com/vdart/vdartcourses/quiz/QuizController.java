package com.vdart.vdartcourses.quiz;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdart.vdartcourses.course.Course;
import com.vdart.vdartcourses.course.CourseService;
import com.vdart.vdartcourses.question.Question;
import com.vdart.vdartcourses.service.ResourceNotFoundException;





@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private CourseService courseService;
    // Get all quizzes
    // Get quiz by id
    // Add a new quiz
    // Delete a quiz by id
    // Update a quiz by id

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN')")
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/quizid/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN')")
    public Quiz getQuizById(@PathVariable ObjectId id) {
        return quizService.getQuizById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + id));
    }

    @PostMapping("/courseid/{courseId}/")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public Quiz saveQuiz(@RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }

    @DeleteMapping("/quizid/{id}/delete")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public ResponseEntity<String> deleteQuizById(@PathVariable ObjectId id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.ok("Quiz deleted successfully");
    }

    @PostMapping("/quizid/{quizid}/question/add")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public Question addQuestionToQuiz(@PathVariable ObjectId quizid, @RequestBody Question question) {
        return quizService.addQuestionToQuiz(quizid, question);
    }
    
    // Attend Quiz
    @GetMapping("/courseid/{courseId}/questions/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN')")
    public List<Question> attendQuiz(@PathVariable ObjectId courseId) {
        return quizService.attendQuiz(courseId);
    }

    
    // @PutMapping("/updateAll")
    // public List<Quiz> updateAllQuizzes() {
    //     List<Course> courses = courseService.getAllCourses();
    //     for (Course course : courses) {
    //         if(quizService.getQuizzesByCourseId(new ObjectId(course.getId())).isEmpty()) {
    //             quizService.saveQuizForCourse(course.getId());
    //         }
    //     }
    //     return quizService.getAllQuizzes();
    // }
}