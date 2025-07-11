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
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setDomain(user.getDomain());
        existingUser.setRole(user.getRole());
        existingUser.setEnrolledCourses(null);
        return userRepo.save(existingUser);
    }
}
