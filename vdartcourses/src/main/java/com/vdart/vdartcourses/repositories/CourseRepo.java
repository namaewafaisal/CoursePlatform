package com.vdart.vdartcourses.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.vdart.vdartcourses.collections.Course;

public interface CourseRepo extends MongoRepository<Course, ObjectId>{

}
