package com.vdart.vdartcourses.enrollment;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/mine")
    @PreAuthorize("isAuthenticated()")
    public List<Enrollment> getUserCourses() {
        return enrollmentService.getUserCourses();
    }

    @PostMapping("/enrollment/add")
    @PreAuthorize("isAuthenticated()")
    public Enrollment enrollUserInCourse(@RequestBody Enrollment enrollment) {
        return enrollmentService.enrollUserInCourse(enrollment);
        
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }
    
      
}
