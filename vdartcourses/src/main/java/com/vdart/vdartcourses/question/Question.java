package com.vdart.vdartcourses.question;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "questions")
public class Question {

    @Id
    private ObjectId id;

    private String questionText; // The text of the question
    private List<String> options; // List of answer options
    private String correctAnswer; // The correct answer option
    private String explanation; // Explanation for the correct answer

    
}
