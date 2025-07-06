package com.vdart.vdartcourses.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vdart.vdartcourses.User;
import com.vdart.vdartcourses.UserService;



@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/post")
    public User postMethodName(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable ObjectId id) {
        return userService.getUserById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    
    }
}
