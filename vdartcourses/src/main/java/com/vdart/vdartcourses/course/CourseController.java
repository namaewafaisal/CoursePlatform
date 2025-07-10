package com.vdart.vdartcourses.course;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdart.vdartcourses.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Get all courses
    // Get course by id
    // Add a new course
    // Delete a course by id
    // Search courses by keyword
    // Update a course by id

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable ObjectId id) {
        return courseService.getCourseById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @PostMapping("/add")
    public Course saveCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }
    @GetMapping("/delete/{id}")
    public void deleteCourseById(@PathVariable ObjectId id) {
        courseService.deleteCourseById(id);
        // You can return a response entity if needed
    }
    
    @GetMapping("/search/{keyword}")
    public List<Course> searchCourses(@PathVariable String keyword) {
        return courseService.searchCourses(keyword);
    }

    // Additional methods for updating courses, etc. can be added here
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable ObjectId id, @RequestBody Course course) {
        //TODO: process PUT request
        return courseService.updateCourse(id, course)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    
}