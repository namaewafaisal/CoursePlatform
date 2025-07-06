package com.vdart.vdartcourses.collections;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {

    @Id
	private ObjectId id;
    
	private String title;
	private String description;
    private String domain;
	private String instructor;
    private String thumbnailUrl;
    private List<SubTopic> subtopics;
    private ObjectId finalQuizId;
    private int noOfEnrolledStudents;
    private List<String> tags;

	
}

class SubTopic {

    private String title;
    private String type; // e.g., "video", "article or text"
    private String content; // text content
    private String videoUrl; // URL for video 
    private ObjectId quizId; // Reference to a quiz if applicable
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getType() {
        return type;
    }
    public SubTopic(String title, String type, String content, String videoUrl, ObjectId quizId) {
        this.title = title;
        this.type = type;
        this.content = content;
        this.videoUrl = videoUrl;
        this.quizId = quizId;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getVideoUrl() {
        return videoUrl;
    }
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public ObjectId getQuizId() {
        return quizId;
    }
    public SubTopic() {
    }
    public void setQuizId(ObjectId quizId) {
        this.quizId = quizId;
    }
}
