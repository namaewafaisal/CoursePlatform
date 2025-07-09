package com.vdart.vdartcourses.course;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepo extends MongoRepository<Course, ObjectId>{

}
