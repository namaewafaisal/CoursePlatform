package com.vdart.vdartcourses.user;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping
    public List<User> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/test")
    public String postMethodName(@RequestParam String username, @RequestParam String email) {
        //TODO: process POST request
        
        System.out.println("Received : " + username + ", " + email);
        return "Success";
    }
    

    @PostMapping("/register")
    public User postMethodName(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable ObjectId id) {
        return userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    
    }
}
