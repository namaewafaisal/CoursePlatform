package com.vdart.vdartcourses.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vdart.vdartcourses.ResourceNotFoundException;



@RestController
@RequestMapping("/api")
public class UserController {
    
    @Autowired
    private UserService userService;

    // For Typical Users
    // Sign up
    // login

    @PostMapping("/auth/signup")
    public String postMethodName(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String domain) {

        return "Received : " + username + ", " + email  + ", " + domain;

    }
    
    @PostMapping("/auth/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        System.out.println("Login attempt with username: " + username + " and password: " + password);
        // Here you would typically check the credentials against a database
        // For now, we just return a success message
        return "Login successful for user: " + username;
    }

    @PostMapping("/auth/signup/post")
    public User postMethodName(@RequestBody User user) {
        
        return userService.saveUser(user);
    }

    // For Admin Users
    // Get all users
    // Get user by id
    // Get user by name
    // Update user
    // Delete user

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable ObjectId id) {
        return userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    
    }
    @GetMapping("/users/search")
    public List<User> getUserByName(@RequestParam String username) {
        return userService.getUserByName(username);
    }
    

    @GetMapping("/users/all")
    public Map<String, List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        Map<String, List<User>> response = new HashMap<>();
        response.put("Users", users);
        return response;
        
    }
    @PutMapping("/users/update/{id}")
    public User saveUser(@PathVariable ObjectId id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    
    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable ObjectId id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    
}