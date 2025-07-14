package com.vdart.vdartcourses.subtopic;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SubtopicRepo extends MongoRepository<Subtopic, ObjectId> {
    
    // Method to find subtopics by course ID
    List<Subtopic> findByCourseId(String courseId);
    
    // Method to find subtopics by title containing a keyword
    List<Subtopic> findByTitleContainingIgnoreCase(String keyword);
    
    // Method to delete subtopics by ID
    void deleteById(ObjectId id);

}
