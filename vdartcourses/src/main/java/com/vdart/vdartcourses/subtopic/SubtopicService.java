package com.vdart.vdartcourses.subtopic;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.ResourceNotFoundException;

@Service
public class SubtopicService {
    @Autowired
    private SubtopicRepo subtopicRepo;

    public Subtopic saveSubtopic(Subtopic subtopic, ObjectId courseId) {
        subtopic.setCourseId(String.valueOf(courseId));
        return subtopicRepo.save(subtopic);
    }
    public List<Subtopic> getSubtopicsByCourseId(String courseId) {
        return subtopicRepo.findByCourseId(courseId);
    }
    public List<Subtopic> getAllSubtopics() {
        return subtopicRepo.findAll();
    }
    public void deleteById(ObjectId id) {
        subtopicRepo.deleteById(id);
    }
    public Subtopic getSubtopicById(ObjectId id) {
        return subtopicRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subtopic not found with id: " + id));
    }
    public Subtopic updateSubtopic(ObjectId id, Subtopic updatedSubtopic) {
        Subtopic existingSubtopic = subtopicRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subtopic not found with id: " + id));
        
        if (updatedSubtopic.getTitle() != null) {
            existingSubtopic.setTitle(updatedSubtopic.getTitle());
        }
        if (updatedSubtopic.getType() != null) {
            existingSubtopic.setType(updatedSubtopic.getType());
        }
        if (updatedSubtopic.getContent() != null) {
            existingSubtopic.setContent(updatedSubtopic.getContent());
        }
        if (updatedSubtopic.getVideoUrl() != null) {
            existingSubtopic.setVideoUrl(updatedSubtopic.getVideoUrl());
        }
        
        return subtopicRepo.save(existingSubtopic);
    }
    
}
