package com.vdart.vdartcourses.subtopic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/subtopics")
public class SubtopicController {
    @Autowired
    private SubtopicService subtopicService;

    // Add a new subtopic to a course
    @PostMapping("/courseid/{courseId}/add")
    @PreAuthorize("hasRole('ADMIN')")   
    public Subtopic addSubtopic(@PathVariable ObjectId courseId, @RequestBody Subtopic subtopic) {
        return subtopicService.saveSubtopic(subtopic, courseId);
    }

    // Get all subtopics for a specific course
    @GetMapping("/courseid/{courseId}/all")
    @PreAuthorize("isAuthenticated()")   
    public List<Subtopic> getSubtopicsByCourseId(@PathVariable String courseId) {
        return subtopicService.getSubtopicsByCourseId(courseId);
    }
    // Delete a subtopic by ID
    @DeleteMapping("/subtopicid/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")   
    public ResponseEntity<String> deleteSubtopicById(@PathVariable ObjectId id) {
        subtopicService.deleteById(id);
        return ResponseEntity.ok("Subtopic deleted successfully");
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")   
    public List<Subtopic> getAllSubtopics() {
        return subtopicService.getAllSubtopics();
    }

    // Use this endpoint to upload a video and return its URL
    @PostMapping("/upload/video")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = Path.of("media/videos/", fileName);
        
        // Create directories if not already present
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        // Return the URL where the video can be accessed
        String videoUrl = "http://localhost:8080/media/videos/" + fileName;
        return ResponseEntity.ok(videoUrl);
    }

    

    
}