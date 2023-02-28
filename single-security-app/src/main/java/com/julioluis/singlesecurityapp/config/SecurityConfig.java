package com.julioluis.singlesecurityapp.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.naming.NoPermissionException;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeHttpRequests()
                .requestMatchers("/members","/finantials").authenticated()
                        .requestMatchers("/contact","/register").permitAll()
                        .and().formLogin()
                        .and().httpBasic();
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
////        UserDetails admin= User.withDefaultPasswordEncoder()
////                .username("admin")
////                .password("123456")
////                .authorities("admin")
////                .build();
//
//        UserDetails admin= User.withUsername("admin")
//                .password("123456")
//                .authorities("admin")
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {return NoOpPasswordEncoder.getInstance();
    }
}
