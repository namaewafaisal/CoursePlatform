package com.vdart.vdartcourses.quiz;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vdart.vdartcourses.question.Question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "quizzes")
public class Quiz {

    @Id
    private ObjectId id;
    private List<ObjectId> questionIds; // List of question IDs

}