package com.example.usermicroservice.service;

import com.example.usermicroservice.UserNotFoundException;
import com.example.usermicroservice.entity.User;
import com.example.usermicroservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

////     Get user by ID Error handling
//    public Optional<User> getUserById(Long userId) {
////        return Optional.ofNullable(userRepository.findById(userId)
////                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId)));
//        return userRepository.findById(userId);
//    }
//Get user by ID LOGGING
//    public User getUserById(Long userId) {
//        logger.info("Fetching user with ID: {}", userId);
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            logger.debug("User found: {}", user.get());
//            return user.get();
//        } else {
//            logger.error("User not found with ID: {}", userId);
//            throw new UserNotFoundException("User not found with ID: " + userId);
//        }
//
//    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId); // Returns Optional<User>
    }

    // Update user
    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword_hash(userDetails.getPassword_hash());
        user.setPhone_number(userDetails.getPhone_number());
        return userRepository.save(user);
    }

    // Delete user
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


}
