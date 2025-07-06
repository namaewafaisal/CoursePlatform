package com.vdart.vdartcourses;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {

    @Id
	private String id;
    
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
