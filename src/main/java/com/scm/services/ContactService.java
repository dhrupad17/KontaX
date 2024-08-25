package com.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scm.entities.Contact;
import com.scm.entities.User;

public interface ContactService {

    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getById(String id);

    void delete(String id);

    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user,int page,int size,String sortField,String sortDirection);

    //Searching Contacts

    Page<Contact> searchByName(String nameKeyword,int size,int page,String sortBy,String order, User user);
    Page<Contact> searchByEmail(String emailKeyword,int size,int page,String sortBy,String order, User user);
    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword,int size,int page,String sortBy,String order, User user);

}
