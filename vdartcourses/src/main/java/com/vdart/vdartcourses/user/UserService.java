package com.vdart.vdartcourses.user;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteUser(ObjectId id) {
        userRepo.deleteById(id);
    }
    public User updateUser(User user) {
        return userRepo.save(user);
    }
}
