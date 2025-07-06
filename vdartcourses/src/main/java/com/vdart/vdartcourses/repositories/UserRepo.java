package com.vdart.vdartcourses.repositories;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vdart.vdartcourses.collections.User;

@Repository
public interface UserRepo extends MongoRepository<User, ObjectId>{


}
