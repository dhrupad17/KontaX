package com.scm.controllers;

import java.security.Principal;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/dashboard")
    public String userDashBoard(){
        return "user/dashboard";
    }

    @RequestMapping(value="/profile")
    public String userProfile(Model model,Authentication authentication){
        

        return "user/profile";
    }

}
