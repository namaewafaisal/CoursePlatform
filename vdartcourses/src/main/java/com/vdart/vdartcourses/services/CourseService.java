package com.vdart.vdartcourses.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.collections.Course;
import com.vdart.vdartcourses.repositories.CourseRepo;

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
}