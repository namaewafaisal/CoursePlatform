package com.vdart.vdartcourses.enrollment;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnrollmentRepo extends MongoRepository<Enrollment, ObjectId>{
   

    List<Enrollment> findByUsername(String username);
    
}
