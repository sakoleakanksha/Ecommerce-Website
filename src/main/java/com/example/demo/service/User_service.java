package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class User_service {

    @Autowired
    private UserRepository userRepo;

    // SIGNUP
    public ResponseEntity<?> signup(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exists");
        }
        userRepo.save(user);
        return ResponseEntity.ok("Signup Successful");
    }

    // LOGIN
    public ResponseEntity<?> login(User user) {
        Optional<User> found = userRepo.findByEmail(user.getEmail());
        if (found.isPresent() && found.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login Successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Email or Password");
    }

    // GET USER BY ID
    public User getUserById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found with id: " + id));
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // UPDATE
    public User updateUser(int id, User userData) {
        User existing = getUserById(id);

        existing.setName(userData.getName());
        existing.setEmail(userData.getEmail());
        existing.setPassword(userData.getPassword());
        existing.setRole(userData.getRole());

        return userRepo.save(existing);
    }

    // DELETE
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}
