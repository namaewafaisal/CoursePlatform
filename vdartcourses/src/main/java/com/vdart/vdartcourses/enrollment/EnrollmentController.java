package com.vdart.vdartcourses.enrollment;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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





@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/mine")
    @PreAuthorize("isAuthenticated()")
    public List<Course> getUserCourses() {
        return enrollmentService.getUserCourses();
    }

    @PostMapping("/add")
    @PreAuthorize("isAuthenticated()")
    public Enrollment enrollUserInCourse(@RequestBody Enrollment enrollment) {
        return enrollmentService.enrollUserInCourse(enrollment);
        
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @DeleteMapping("/enrollmentid/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEnrollment(@PathVariable ObjectId id) {
        enrollmentService.deleteEnrollment(id);
    }

    @PutMapping("/enrollmentid/{id}/update")
    @PreAuthorize("isAuthenticated()")
    public Enrollment updateEnrollment(@PathVariable ObjectId id, @RequestBody Enrollment enrollment) {
        
        return enrollmentService.updateEnrollment(id,enrollment);
    }

    @DeleteMapping("/deleteall")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAllEnrollments(){
        enrollmentService.deleteAll();
        return "Success";
    }
    
    
      
}
