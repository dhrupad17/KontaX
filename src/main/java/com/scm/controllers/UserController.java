package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value="/dashboard")
    public String userDashBoard(){
        return "user/dashboard";
    }

    @RequestMapping(value="/profile")
    public String userProfile(){
        return "user/profile";
    }

}
