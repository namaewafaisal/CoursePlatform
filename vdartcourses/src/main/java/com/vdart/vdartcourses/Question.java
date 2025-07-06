package com.vdart.vdartcourses;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Question {

    @Id
    private ObjectId id;

    private String questionText; // The text of the question
    private List<String> options; // List of answer options
    private String correctAnswer; // The correct answer option
    private String explanation; // Explanation for the correct answer

    // Getters and Setters
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Question() {
    }

    public Question(ObjectId id, String questionText, List<String> options, String correctAnswer, String explanation) {
        this.id = id;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
