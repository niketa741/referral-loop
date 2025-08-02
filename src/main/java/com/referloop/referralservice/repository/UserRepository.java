package com.referloop.referralservice.repository;

import com.referloop.referralservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByLinkedinId(String linkedinId);
}