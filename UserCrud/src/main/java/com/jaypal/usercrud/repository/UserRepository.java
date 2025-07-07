package com.jaypal.usercrud.repository;

import com.jaypal.usercrud.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    void deleteUserById(String id);
}
