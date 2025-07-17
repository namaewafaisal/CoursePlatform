package com.vdart.vdartcourses.user;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vdart.vdartcourses.ResourceNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User saveUser(User user) {
        return userRepo.save(user);
    }
    public Optional<User> getUserById(ObjectId id) {
        return userRepo.findById(id);
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public List<User> getUserByName(String username){
        return userRepo.findByUsernameContainingIgnoreCase(username);
    }
    public void deleteUser(ObjectId id) {
        userRepo.deleteById(id);
    }
    public User updateUser(ObjectId id,User user) {
        User existingUser = userRepo.findById(id).orElseThrow(() -> 
            new ResourceNotFoundException("User not found with id: " + id));
        if(user.getUsername() != null) existingUser.setUsername(user.getUsername());
        if(user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if(user.getPassword() != null) existingUser.setPassword(user.getPassword());
        if(user.getDomain() != null) existingUser.setDomain(user.getDomain());
        if(user.getRole() != null) existingUser.setRole(user.getRole());
        return userRepo.save(existingUser);
    }
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
