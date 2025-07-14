package com.vdart.vdartcourses.subtopic;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
