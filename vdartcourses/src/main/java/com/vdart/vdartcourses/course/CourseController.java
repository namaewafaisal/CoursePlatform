package com.vdart.vdartcourses.course;

import java.util.List;
import java.util.Optional;

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

import com.vdart.vdartcourses.quiz.QuizService;
import com.vdart.vdartcourses.service.ResourceNotFoundException;
import com.vdart.vdartcourses.subtopic.Subtopic;
import com.vdart.vdartcourses.subtopic.SubtopicService;


@RestController
@RequestMapping("/courses")
public class CourseController {


    @Autowired
    private CourseService courseService;
    @Autowired
    private SubtopicService subtopicService;

    @Autowired
    private QuizService quizService;

    // Get all courses
    // Get course by id
    // Add a new course
    // Delete a course by id
    // Search courses by keyword
    // Update a course by id

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courseid/{id}")
    @PreAuthorize("permitAll()")
    public Course getCourseById(@PathVariable ObjectId id) {
        return courseService.getCourseById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public Course createCourse(@RequestBody Course course) {
        if (course.getCourseKey() == null || course.getCourseKey().isEmpty()) {
            throw new IllegalArgumentException("Course key cannot be null or empty");
        }
        if (course.getTitle() == null || course.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Course title cannot be null or empty");
        }
        courseService.saveCourse(course);
        quizService.saveQuizForCourse(course.getId());
        return course;
    }

    @DeleteMapping("/courseid/{id}/delete")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public ResponseEntity<String> deleteCourseById(@PathVariable ObjectId id) {
        if (!courseService.getCourseById(id).isPresent()) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        // Check if the course exists before attempting to delete
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
    
    @GetMapping("/search/coursetitlekeyword/{keyword}")
    @PreAuthorize("permitAll()")

    public List<Course> searchCourses(@PathVariable String keyword) {
        return courseService.searchCourses(keyword);
    }

    @GetMapping("/coursekey/{courseKey}")
    @PreAuthorize("permitAll()")
    public Optional<Course> getCourseByCourseKey(@PathVariable String courseKey) {
        return courseService.getCourseByCourseKey(courseKey);
    }

    @GetMapping("/courseid/{courseId}/subtopic/all")
    @PreAuthorize("permitAll()")
    public List<Subtopic> getSubtopicsByCourseId(@PathVariable String courseId) {
        return subtopicService.getSubtopicsByCourseId(courseId);
    }

    // Additional methods for updating courses, etc. can be added here
      // View a subtopic of a course

    // @GetMapping("/coursekey/{courseKey}/subtopic/{subtopic}")
    // @PreAuthorize("isAuthenticated")
    // public ResponseEntity<String> watchASubTopic(@PathVariable String courseKey, @PathVariable String subtopic) {
    // Course course = courseService.getCourseByCourseKey(courseKey)
    //         .orElseThrow(() -> new ResourceNotFoundException("Course not found with key: " + courseKey));
    //     List<Subtopic> subtopics = subtopicService.getSubtopicsByCourseId(course.getId());
    //     for (Subtopic sub : subtopics) {
    //         if (sub.getTitle().equalsIgnoreCase(subtopic)) {
    //             String videoUrl = sub.getVideoUrl();
    //             return ResponseEntity.ok(videoUrl);
    //         }
    //     }
    //     return ResponseEntity.notFound().build();
    // }

    @PutMapping("/coursekey/{courseKey}/update")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public Course updateCourseDetails(@PathVariable("courseKey") String courseKey, @RequestBody Course course) {
        Course oldcourse = courseService.getCourseByCourseKey(courseKey)
            .orElseThrow(() -> new ResourceNotFoundException("Not found course"));
        // Update only existing fields
        if(course.getTitle()!= null) oldcourse.setTitle(course.getTitle());
        if(course.getCourseKey()!= null) oldcourse.setCourseKey(course.getCourseKey());
        if(course.getDescription()!= null) oldcourse.setDescription(course.getDescription());
        if(course.getDomain()!= null) oldcourse.setDomain(course.getDomain());
        if(course.getInstructor()!= null) oldcourse.setInstructor(course.getInstructor());
        if(course.getThumbnailUrl()!= null) oldcourse.setThumbnailUrl(course.getThumbnailUrl());
        // Save updated course
        return courseService.saveCourse(oldcourse);
    }
    @PostMapping("/courseid/{id}/subtopic/add")
    @PreAuthorize("hasAnyRole('ADMIN','SUBADMIN','FACULTY')")
    public Subtopic addSubtopicToCourse(@PathVariable ObjectId id, @RequestBody Subtopic subtopic) {
        return subtopicService.saveSubtopic(subtopic, id);
    }

    

}