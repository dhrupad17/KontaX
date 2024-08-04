package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

     // this is showing login page
     @GetMapping("/login")
     public String login() {
         return new String("login");
     }
 

    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("name", "TechnoLearn Solutions");
        model.addAttribute("InstagramHandle", "chakro_bore_tea");
        model.addAttribute("script", "scriptFragment :: script"); // Add script fragment
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", false);
        model.addAttribute("script", "scriptFragment :: script"); // Add script fragment
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(Model model){
        System.out.println("Services Page loading");
        model.addAttribute("script", "scriptFragment :: script"); // Add script fragment
        return "services";
    }

    @RequestMapping("/contact")
    public String contactPage(Model model){
        System.out.println("Contact Page loading");
        model.addAttribute("script", "scriptFragment :: script"); // Add script fragment
        return "contact";
    }

    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
    
        model.addAttribute("userForm", userForm);

        return "register";
    }

     @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("Processing registration");
        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data
        if (rBindingResult.hasErrors()) {
            return "register";
        }


        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic(
                "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");

        User savedUser = userService.saveUser(user);

        System.out.println("user saved :");

        // message = "Registration Successful"

        // add the message:

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        // redirectto login page
        return "redirect:/register";
    }

}
