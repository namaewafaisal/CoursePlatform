package com.vdart.vdartcourses.course;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courses")
public class Course {

    @Id
	private ObjectId id;
    
    @Indexed(unique = true)
    private String courseKey; // Unique identifier for the course, can be a slug or unique string
	
    private String title;
	private String description;
    private String domain;
    private String instructor;
    private String thumbnailUrl;
    private int noOfEnrolledStudents;
    private List<String> tags;
    private ObjectId finalQuizId; // Reference to the final quiz for the course
	public String getId() {
        return id!= null ? id.toHexString() : null;
    }
}