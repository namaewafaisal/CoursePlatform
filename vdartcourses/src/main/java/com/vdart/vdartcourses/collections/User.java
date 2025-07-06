package com.vdart.vdartcourses.collections;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {
    
    @Id
    private ObjectId id;
    
    private String username;
    private String email;
    private String password;
    private String role; // e.g., "student", "instructor", "admin"
    private String domain;
    private List<String> enrolledCourses;
    public User(ObjectId id, String username, String email, String password, String role, String domain,
            List<String> enrolledCourses) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.domain = domain;
        this.enrolledCourses = enrolledCourses;
    }
    public User() {
    }
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }
    public void setEnrolledCourses(List<String> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
    

}
