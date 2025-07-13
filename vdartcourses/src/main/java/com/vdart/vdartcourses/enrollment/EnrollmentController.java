package com.vdart.vdartcourses.enrollment;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/{userId}/courses")
    public List<Enrollment> getUserCourses(@PathVariable ObjectId userId) {
        return enrollmentService.getUserCourses(userId);
    }

    @PostMapping("/{userId}/{courseId}/enroll")
    public String enrollUserInCourse(@PathVariable ObjectId userId, @PathVariable ObjectId courseId, @RequestBody Enrollment enrollment) {
        enrollmentService.enrollUserInCourse(userId, courseId, enrollment);
        return "User enrolled successfully in course with id: " + courseId;
    }
    
    
    
}
