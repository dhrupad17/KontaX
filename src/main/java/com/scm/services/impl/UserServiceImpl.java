package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.helpers.ResourceNotFoundException;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // TODO Auto-generated method stub
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //set role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        // TODO Auto-generated method stub
       return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        // TODO Auto-generated method stub
        User user2=userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not Found"));

        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        User save=userRepo.save(user2);

        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        // TODO Auto-generated method stub
        User user2=userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not Found"));

        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExsist(String userId) {
        // TODO Auto-generated method stub
        User user2=userRepo.findById(userId).orElse(null);
        return user2 !=null ? true: false;
    }

    @Override
    public boolean isUserExsistByEmail(String email) {
        // TODO Auto-generated method stub
        User user=userRepo.findByEmail(email).orElse(null);
        return user!=null ? true: false;
    }

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        // TODO Auto-generated method stub
        // return userRepo.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));

        return userRepo.findByEmail(email).orElse(null);
    }

}
