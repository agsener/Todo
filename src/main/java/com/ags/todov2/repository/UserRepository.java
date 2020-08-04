package com.ags.todov2.repository;

import com.ags.todov2.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    Optional<User> findById(String id);
}
