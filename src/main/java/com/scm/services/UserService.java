package com.scm.services;

import com.scm.entities.User;


import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    void deleteUser(String id);

    boolean isUserExsist(String userId);

    boolean isUserExsistByEmail(String email);

    List<User> getAllUsers();

}
