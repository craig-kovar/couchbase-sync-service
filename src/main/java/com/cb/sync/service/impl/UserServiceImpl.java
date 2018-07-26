package com.cb.sync.service.impl;

import com.cb.sync.datasource.user.entity.User;
import com.cb.sync.datasource.user.repo.UserRepo;
import com.cb.sync.exception.ApiException;
import com.cb.sync.exception.ErrorType;
import com.cb.sync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepo userRepo;

  @Override
  public User getUserById(Long id) {
    return userRepo.findById(id).orElseThrow(() -> new ApiException(ErrorType.USER_NOT_FOUND));
  }

  @Override
  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  @Override
  public User createUser(@Valid User user) {
    return userRepo.save(user);
  }

  @Override
  public void deleteUser(Long userId) {
    // The user should exist
    this.getUserById(userId);
    userRepo.deleteById(userId);
  }

  @Override
  public Boolean exists(Long id) {
    return userRepo.existsById(id);
  }

}
