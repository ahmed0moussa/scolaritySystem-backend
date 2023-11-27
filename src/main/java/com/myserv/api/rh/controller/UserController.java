package com.myserv.api.rh.controller;

import com.myserv.api.rh.model.MenuItem;
import com.myserv.api.rh.model.User;
import com.myserv.api.rh.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private  UserService userService;

    @GetMapping("/api/users/all")
    public List<User> getAllUsers() {
        return this.userService.findAllUsers();
    }
    @GetMapping("/api/users/{userId}")
    public Optional<User> getAllUsers(@PathVariable String userId) {
        return this.userService.findById(userId);
    }
    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userService.deleteById(id);
    }
    @PutMapping("api/users/updateMenu/{id}")
    public List<MenuItem> updateMenu(@PathVariable String id,@RequestBody List<MenuItem> menuItems){
        return userService.updateMenuUser(id,menuItems);
    }
}
