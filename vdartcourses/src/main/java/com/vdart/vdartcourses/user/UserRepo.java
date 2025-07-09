package com.vdart.vdartcourses.user;


import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends MongoRepository<User, ObjectId>{

    List<User> findByUsernameContainingIgnoreCase(String username);
}
