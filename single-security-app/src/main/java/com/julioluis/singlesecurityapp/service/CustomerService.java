package com.julioluis.singlesecurityapp.service;

import com.julioluis.singlesecurityapp.entities.Customer;
import com.julioluis.singlesecurityapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer=customerRepository.findCustomerByUsername(username);

        if(Objects.isNull(customer)) {
            throw new UsernameNotFoundException("User not found");
        }

        List authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(customer.getRole()));


        return new User(customer.getUsername(),customer.getPassword(),authorities);
    }
}
