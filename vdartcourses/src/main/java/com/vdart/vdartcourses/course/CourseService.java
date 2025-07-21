package com.vdart.vdartcourses.course;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.service.ResourceNotFoundException;
import com.vdart.vdartcourses.subtopic.Subtopic;
import com.vdart.vdartcourses.subtopic.SubtopicRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private SubtopicRepo subtopicRepo;

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
    public Optional<Course> getCourseByCourseKey(String courseKey){
        return courseRepo.findByCourseKey(courseKey);
    }
    // Method to delete a course by ID
    public void deleteCourseById(ObjectId id) {
        courseRepo.deleteById(id);
    }

    // Method to search courses by keyword
    public List<Course> searchCourses(String keyword) {
        return courseRepo.findByTitleContainingIgnoreCase(keyword);
    }

    // Method to get subtopics of a course
    public List<Subtopic> getSubtopicsByCourseId(String courseId) {
        List<Subtopic> subtopics = subtopicRepo.findByCourseId(courseId);
        if (subtopics.isEmpty()) {
            throw new ResourceNotFoundException("No subtopics found for course id: " + courseId);
        }
        return subtopics;
    }

    // Method to update a course
    public Optional<Course> updateCourse(ObjectId id, Course course) {
        Optional<Course> existingCourse = courseRepo.findById(id);
        if (existingCourse.isPresent()) {
            Course updatedCourse = existingCourse.get();
            if (course.getTitle() != null) updatedCourse.setTitle(course.getTitle());
            if (course.getDescription() != null) updatedCourse.setDescription(course.getDescription());
            if (course.getInstructor() != null) updatedCourse.setInstructor(course.getInstructor());
            if (course.getDomain() != null) updatedCourse.setDomain(course.getDomain());
            if (course.getThumbnailUrl() != null) updatedCourse.setThumbnailUrl(course.getThumbnailUrl());
            if (course.getFinalQuizId() != null) updatedCourse.setFinalQuizId(course.getFinalQuizId());
            if (course.getTags() != null) updatedCourse.setTags(course.getTags());
            // Update other fields as necessary, only if not null
            return Optional.of(courseRepo.save(updatedCourse));
        }
        return Optional.empty();
    }
    public Optional<Course> getCourseByTitle(String title) {
        return courseRepo.findByTitle(title);
    }
}