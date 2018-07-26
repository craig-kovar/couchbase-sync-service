package com.cb.sync.controller;

import com.cb.sync.datasource.user.entity.User;
import com.cb.sync.datasource.user.repo.UserRepo;
import com.cb.sync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserRepo userRepo;

  @Autowired UserService userService;

  @GetMapping("/all-users")
  @ResponseStatus(HttpStatus.OK)
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User getUserById(@PathVariable(value = "id") Long userId) {
    return userService.getUserById(userId);
  }

  @PostMapping("/create-user")
  @ResponseStatus(HttpStatus.CREATED)
  public User createUser(@Valid @RequestBody User user) {
    return userService.createUser(user);
  }

  @DeleteMapping("/delete-user/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
    userService.deleteUser(userId);
    return ResponseEntity.ok().build();
  }
}
