package com.julioluis.singlesecurityapp.controllers;

import com.julioluis.singlesecurityapp.entities.Customer;
import com.julioluis.singlesecurityapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        ResponseEntity response=null;
        try {
            String hashPwd=passwordEncoder.encode(customer.getPassword());
            customer.setPassword(hashPwd);
            Customer savedCustomer=customerRepository.save(customer);
            if(savedCustomer.getId()>0) {
                response=ResponseEntity.status(HttpStatus.CREATED)
                        .body("Given customer has been created");
            }
        }catch (Exception ex) {
            response=ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An execption occured due to "+ ex.getMessage());
        }

        return response;
    }
}
