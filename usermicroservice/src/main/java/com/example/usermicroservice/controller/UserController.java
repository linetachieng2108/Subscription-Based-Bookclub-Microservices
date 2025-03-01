package com.example.usermicroservice.controller;

import com.example.usermicroservice.entity.User;
import com.example.usermicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable Long user_id) {
        return userService.getUserById(user_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update user
    @PutMapping("/{user_id}")
    public User updateUser(@PathVariable Long user_id, @RequestBody User userDetails) {
        return userService.updateUser(user_id, userDetails);
    }

    // Delete user
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }
// EXPOSED ENDPOINT TO ADD USER TO A BOOKCLUB
//    use port ya bookclub microservice
    @GetMapping("/exists/{user_id}")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable Long user_id) {
        return ResponseEntity.ok(userService.getUserById(user_id).isPresent());
    }
}
