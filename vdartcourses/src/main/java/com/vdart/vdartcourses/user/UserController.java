package com.vdart.vdartcourses.user;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    @Autowired 
    private PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()) );
        return userService.saveUser(user);
    }

    // For Admin Users
    // Get all users
    // Get user by id
    // Get user by name
    // Update user
    // Delete user

    @GetMapping("/userid/{id}")
    public User getUserById(@PathVariable ObjectId id) {
        return userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    
    }
    @GetMapping("/search")
    public List<User> getUserByName(@RequestParam String username) {
        return userService.getUserByName(username);
    }
    

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
        
    }
    @PutMapping("/userid/{id}/update")
    public User saveUser(@PathVariable ObjectId id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/userid/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable ObjectId id) {
        if (!userService.getUserById(id).isPresent()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    
}