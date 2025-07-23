package com.vdart.vdartcourses.enrollment;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnrollmentRepo extends MongoRepository<Enrollment, ObjectId>{
   

    List<Enrollment> findByUsername(String username);
    Optional<Enrollment> findByUsernameAndCourseId(String username , ObjectId courseId);
}
