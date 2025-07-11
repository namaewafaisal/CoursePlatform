package com.vdart.vdartcourses.question;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vdart.vdartcourses.quiz.Quiz;

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
    private ObjectId quizId; // Reference to the quiz this question belongs to

    public String getId() {
        return id != null ? id.toHexString() : null;
    }
    public String getQuizId() {
        return quizId != null ? quizId.toHexString() : null;
    }
    public void setQuizId(ObjectId quizId) {
        this.quizId = quizId;
    }
    
}
