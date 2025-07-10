package com.vdart.vdartcourses.course;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepo extends MongoRepository<Course, ObjectId>{

    // Additional query methods can be defined here if needed
    List<Course> findByTitleContainingIgnoreCase(String keyword);

    Optional<Course> findByCourseId(String courseId);

    Optional<Course> findByTitle(String title);
}
