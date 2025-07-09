package com.vdart.vdartcourses.quiz;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "quizzes")
public class Quiz {

    @Id
    private ObjectId id;

    
    private List<Question> questions;
}
@Data
@NoArgsConstructor
@AllArgsConstructor


class Question {

    @Id
    private ObjectId id;

    private String questionText; // The text of the question
    private List<String> options; // List of answer options
    private String correctAnswer; // The correct answer option
    private String explanation; // Explanation for the correct answer

    
}
