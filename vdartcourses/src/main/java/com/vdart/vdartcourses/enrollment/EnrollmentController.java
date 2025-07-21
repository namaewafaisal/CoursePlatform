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



@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/mine")
    @PreAuthorize("isAuthenticated()")
    public List<Enrollment> getUserCourses() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return enrollmentService.getUserCourses(username);
    }

    @PostMapping("/enrollment/add")
    @PreAuthorize("isAuthenticated()")
    public void enrollUserInCourse(@RequestBody Enrollment enrollment) {

        enrollmentService.enrollUserInCourse(enrollment);
        
    }
    
    
    
}
