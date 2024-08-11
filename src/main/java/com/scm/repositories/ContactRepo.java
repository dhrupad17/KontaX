package com.scm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.entities.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String>{
    
}
