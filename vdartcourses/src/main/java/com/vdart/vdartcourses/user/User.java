package com.vdart.vdartcourses.user;

import java.util.Optional;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vdart.vdartcourses.service.Role;

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
    
    @Indexed(unique = true)
    private String email;
    private String password;
    
    private Set<Role> roles;
    private String domain;
   
    public String getId() {
        return id != null ? id.toHexString() : null;
    }

    public Set<Role> getRoles() {
        return roles;
    }   
}
