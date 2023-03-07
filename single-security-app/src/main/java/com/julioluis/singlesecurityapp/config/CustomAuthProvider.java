package com.julioluis.singlesecurityapp.config;

import com.julioluis.singlesecurityapp.entities.Customer;
import com.julioluis.singlesecurityapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String pass=authentication.getCredentials().toString();

        Customer customer=customerRepository.findByEmail(username);
        if(Objects.nonNull(customer)) {
            if(passwordEncoder.matches(pass,customer.getPwd())) {
                List<GrantedAuthority> authorities=new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customer.getRole()));
                return new UsernamePasswordAuthenticationToken(username,pass,authorities);
            }else {
                throw new BadCredentialsException("Invalid Password");
            }
        }else {
            throw new BadCredentialsException("No user register with this details");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
