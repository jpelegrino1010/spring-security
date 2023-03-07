package com.julioluis.singlesecurityapp.repositories;


import com.julioluis.singlesecurityapp.entities.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {


}
