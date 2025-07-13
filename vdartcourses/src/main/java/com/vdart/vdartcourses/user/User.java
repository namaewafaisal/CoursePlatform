package com.vdart.vdartcourses.user;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "users")
public class User {
    
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String username;
    
    private String email;
    private String password;
    private String role; // e.g., "student", "instructor", "admin"
    private String domain;
   
    public String getId() {
        return id != null ? id.toHexString() : null;
    }   
}
