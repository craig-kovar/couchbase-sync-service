package com.cb.sync.service;

import com.cb.sync.datasource.user.entity.User;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

  User getUserById(Long id);

  List<User> getAllUsers();

  User createUser(@Valid User user);

  void deleteUser(Long id);

  Boolean exists(Long id);

}
