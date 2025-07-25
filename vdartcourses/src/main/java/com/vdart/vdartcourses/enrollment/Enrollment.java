package com.vdart.vdartcourses.enrollment;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "enrolled")
public class Enrollment {

    private ObjectId courseId;
    private String username;
    @Id
    private ObjectId id; // Unique identifier for the enrollment

    private String enrollmentDate; // Date of enrollment in ISO format
    private String completionDate; // Date of course completion in ISO format
    private boolean isCompleted; // Indicates if the course has been completed
    private String progress; // Progress in the course, e.g., "50%" or "Completed"
    private String certificateUrl; // URL to the certificate of completion, if applicable
    private int score; // Score achieved in the final quiz

    public String getId() {
        return id != null ? id.toHexString() : null;
    }
    public String getCourseId(){
        return courseId != null ? courseId.toHexString() : null;
    }
    public boolean getIsCompleted() {
        return isCompleted;
    }
    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
