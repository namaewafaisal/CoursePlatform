package com.vdart.vdartcourses.course;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    // Method to save a course
    public Course saveCourse(Course course) {
        return courseRepo.save(course);
    }
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }
    // Method to get a course by ID
    public Optional<Course> getCourseById(ObjectId id) {
        return courseRepo.findById(id);
    }
    // Method to delete a course by ID
    public void deleteCourseById(ObjectId id) {
        courseRepo.deleteById(id);
    }

    // Method to search courses by keyword
    public List<Course> searchCourses(String keyword) {
        return courseRepo.findByTitleContainingIgnoreCase(keyword);
    }

    // Method to update a course
    public Optional<Course> updateCourse(ObjectId id, Course course) {
        Optional<Course> existingCourse = courseRepo.findById(id);
        if (existingCourse.isPresent()) {
            Course updatedCourse = existingCourse.get();
            updatedCourse.setTitle(course.getTitle());
            updatedCourse.setDescription(course.getDescription());
            updatedCourse.setInstructor(course.getInstructor());
            updatedCourse.setDomain(course.getDomain());
            updatedCourse.setThumbnailUrl(course.getThumbnailUrl());
            updatedCourse.setSubtopics(course.getSubtopics());
            updatedCourse.setFinalQuizId(course.getFinalQuizId());
            updatedCourse.setNoOfEnrolledStudents(course.getNoOfEnrolledStudents());
            updatedCourse.setTags(course.getTags());
            // Update other fields as necessary
            return Optional.of(courseRepo.save(updatedCourse));
        }
        return Optional.empty();
    }
}