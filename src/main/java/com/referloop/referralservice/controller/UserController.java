package com.referloop.referralservice.controller;

import com.referloop.referralservice.model.User;
import com.referloop.referralservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userRepo.findById(id).orElseThrow();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}