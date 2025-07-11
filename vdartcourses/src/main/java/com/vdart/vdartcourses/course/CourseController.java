package com.vdart.vdartcourses.course;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdart.vdartcourses.ResourceNotFoundException;





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
    // View a subtopic of a course

    @GetMapping("/{courseKey}/{subtopic}")
    public ResponseEntity<String> watchASubTopic(@PathVariable String courseKey, @PathVariable String subtopic) {
        Course  course = courseService.getCourseByCourseKey(courseKey).orElseThrow(()-> new ResourceNotFoundException("Course not found " + courseKey));
        Optional<SubTopic> found = course.getSubtopics().stream().filter(s -> s.getTitle().equalsIgnoreCase(subtopic)).findFirst();
        if (found.isEmpty()){
            throw new ResourceNotFoundException("Subtopic not found");
        }
        return ResponseEntity.ok(found.get().getVideoUrl());
    }
    
    @PutMapping("update/{courseTitle}")
    public Course updateCourseDetails(@PathVariable("courseTitle") String courseTitle, @RequestBody Course course) {
        Course oldcourse = courseService.getCourseByTitle(courseTitle)
            .orElseThrow(() -> new ResourceNotFoundException("Not found course"));
        // Update only existing fields
        oldcourse.setTitle(course.getTitle());
        oldcourse.setCourseKey(course.getCourseKey());
        oldcourse.setDescription(course.getDescription());
        oldcourse.setDomain(course.getDomain());
        oldcourse.setInstructor(course.getInstructor());
        oldcourse.setSubtopics(course.getSubtopics());
        // Save updated course
        return courseService.saveCourse(oldcourse);
    }
    @GetMapping("/print-objectids")
    public ResponseEntity<String> printAllCourseObjectIds() {
        List<Course> courses = courseService.getAllCourses();
        System.out.println("Course ObjectIds:");
        for (Course course : courses) {
            System.out.println(course.getId()); // Assumes getId() returns ObjectId
        }
        return ResponseEntity.ok("Printed all course ObjectIds to terminal.");
}
}