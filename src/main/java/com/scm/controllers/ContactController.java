package com.scm.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger=org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm=new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }
    
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication authentication, HttpSession session){

        if(result.hasErrors()){
            session.setAttribute("message", Message.builder().content("Please Correct the following errors.").type(MessageType.red).build());

            return "user/add_contact";
        }
        
        //form -> Contact
        String username=Helper.getEmailOfLoggedInUser(authentication);

        User user=userService.getUserByEmail(username);

        logger.info("file Information : {}",contactForm.getContactImage().getOriginalFilename());

        String filename=UUID.randomUUID().toString();
        
        String fileURL=imageService.uploadImage(contactForm.getContactImage(),filename);

        Contact contact=new Contact();
        contact.setName(contactForm.getName());
        contact.setFavourite(contactForm.isFavourite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedlnLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);
        contactService.save(contact);
        System.out.println(contactForm);

        session.setAttribute("message", Message.builder().content("You have successfully added a new contact").type(MessageType.green).build());

        return "redirect:/user/contacts/add";
    }

    @RequestMapping
    public String viewContacts(Model model, Authentication authentication){

        //load all the user Contacts
        String username=Helper.getEmailOfLoggedInUser(authentication);

        User user=userService.getUserByEmail(username);

        List<Contact> contacts=contactService.getByUser(user);

        model.addAttribute("contacts", contacts);
        
        return "user/contacts";
    }
}
