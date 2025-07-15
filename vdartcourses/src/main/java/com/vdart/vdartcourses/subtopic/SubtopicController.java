package com.vdart.vdartcourses.subtopic;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subtopics")
public class SubtopicController {
    @Autowired
    private SubtopicService subtopicService;

    // Add a new subtopic to a course
    @PostMapping("/courseid/{courseId}/add")
    public Subtopic addSubtopic(@PathVariable ObjectId courseId, @RequestBody Subtopic subtopic) {
        return subtopicService.saveSubtopic(subtopic, courseId);
    }

    // Get all subtopics for a specific course
    @GetMapping("/courseid/{courseId}/all")
    public List<Subtopic> getSubtopicsByCourseId(@PathVariable String courseId) {
        return subtopicService.getSubtopicsByCourseId(courseId);
    }
    // Delete a subtopic by ID
    @DeleteMapping("/subtopicid/{id}/delete")
    public ResponseEntity<String> deleteSubtopicById(@PathVariable ObjectId id) {
        subtopicService.deleteById(id);
        return ResponseEntity.ok("Subtopic deleted successfully");
    }
    @GetMapping("/all")
    public List<Subtopic> getAllSubtopics() {
        return subtopicService.getAllSubtopics();
    }
}