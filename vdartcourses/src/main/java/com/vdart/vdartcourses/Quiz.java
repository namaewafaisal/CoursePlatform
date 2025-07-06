package com.vdart.vdartcourses;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quizzes")
public class Quiz {

    @Id
    private ObjectId id;

    
    private List<Question> questions;
}
