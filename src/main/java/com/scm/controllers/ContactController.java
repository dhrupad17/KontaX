package com.scm.controllers;


import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.forms.ContactSearchForm;
import com.scm.helpers.AppConstants;
import com.scm.helpers.Helper;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;


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
    public String viewContacts(
        @RequestParam(value="page", defaultValue = "0") int page,
        @RequestParam(value="size",defaultValue = AppConstants.PAGE_SIZE+ "") int size,
        @RequestParam(value = "sortBy",defaultValue = "name") String sortBy,
        @RequestParam(value="direction", defaultValue = "asc") String direction,
        Model model, Authentication authentication){

        //load all the user Contacts
        String username=Helper.getEmailOfLoggedInUser(authentication);

        User user=userService.getUserByEmail(username);

        Page<Contact> pageContact=contactService.getByUser(user,page,size,sortBy,direction);

        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        model.addAttribute("contactSearchForm", new ContactSearchForm());
        
        return "user/contacts";
    }

    @RequestMapping("/search")
    public String searchHandler(

        @ModelAttribute ContactSearchForm contactSearchForm,
        @RequestParam(value="size",defaultValue = AppConstants.PAGE_SIZE+ "") int size,
        @RequestParam(value="page",defaultValue = "0") int page,
        @RequestParam(value="sortBy",defaultValue="name") String sortBy,
        @RequestParam(value="direction",defaultValue = "asc") String direction, 
        Model model,
        Authentication authentication)
        {

            logger.info("field {} keyword {}",contactSearchForm.getField(),contactSearchForm.getValue());

            var user=userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));



            Page<Contact> pageContact=null;
            if(contactSearchForm.getField().equalsIgnoreCase("name")){
                pageContact=contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction, user);
            }
            else if(contactSearchForm.getField().equalsIgnoreCase("email")){
                pageContact=contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction, user);
            }else if(contactSearchForm.getField().equalsIgnoreCase("phone")){
                pageContact=contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy, direction, user);
            }

            logger.info("pageContact {} ",pageContact);
            model.addAttribute("contactSearchForm", contactSearchForm);
            model.addAttribute("pageContact", pageContact);

            model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

            return "user/search";

    }

    @RequestMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable String contactId,
                                HttpSession session){
        
        contactService.delete(contactId);
        logger.info("contactId {} deleted ", contactId);

        session.setAttribute("message",
            Message.builder()
                   .content("Contact is Deleted Successfully !!")
                   .type(MessageType.red)
                   .build());
        
        return "redirect:/user/contacts";
    }

    @GetMapping("/view/{contactId}")
    public String updateContactFormView(
        @PathVariable("contactId") String contactId, 
        Model model){

            var contact=contactService.getById(contactId);

            ContactForm contactForm=new ContactForm();

            contactForm.setName(contact.getName());
            contactForm.setEmail(contact.getEmail());
            contactForm.setPhoneNumber(contact.getPhoneNumber());
            contactForm.setAddress(contact.getAddress());
            contactForm.setDescription(contact.getDescription());
            contactForm.setFavourite(contact.isFavourite());
            contactForm.setWebsiteLink(contact.getWebsiteLink());
            contactForm.setLinkedlnLink(contact.getLinkedInLink());
            contactForm.setPicture(contact.getPicture());

            model.addAttribute("contactForm", contactForm);
            model.addAttribute("contactId", contactId);

            return "user/update_contact_view";

        }

    @RequestMapping(value="/update/{contactId}", method=RequestMethod.POST)
    public String updateContact(@PathVariable("contactId") String contactId, @ModelAttribute ContactForm contactForm, Model model){

        var con=new Contact();
        con.setName(contactForm.getName());
        con.setEmail(contactForm.getEmail());
        con.setPhoneNumber(contactForm.getPhoneNumber());
        con.setAddress(contactForm.getAddress());
        con.setDescription(contactForm.getDescription());
        con.setFavourite(contactForm.isFavourite());
        con.setWebsiteLink(contactForm.getWebsiteLink());
        con.setLinkedInLink(contactForm.getLinkedlnLink());
        con.setPicture(contactForm.getPicture());

        var updateCon=contactService.update(con);
        logger.info("updated");

        return "Hello Hii";
    }
}
