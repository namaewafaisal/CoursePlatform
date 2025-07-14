package com.vdart.vdartcourses.subtopic;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class SubTopic {

    @Id
    private ObjectId id; // Unique identifier for the subtopic

    private String title;
    private String type; // e.g., "video", "article or text"
    private String content; // text content
    private String videoUrl; // URL for video
    private String courseId;

    public String getId() {
        return id != null ? id.toHexString() : null;
    }
   
   
}
