package com.julioluis.singlesecurityapp.config;


import com.julioluis.singlesecurityapp.filters.CsrfCookiFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHander=new CsrfTokenRequestAttributeHandler();
        requestHander.setCsrfRequestAttributeName("_csrf");
        http.securityContext().requireExplicitSave(false)
                .and().sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config=new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);

                        return config;
                    }
                })
                .and()
                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHander).ignoringRequestMatchers("/contact","/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())).
                addFilterAfter(new CsrfCookiFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
//                .requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
//                .requestMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
//                .requestMatchers("/myCards").hasAuthority("VIEWCARDS")
//                .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")

                .requestMatchers("/myAccount").hasRole("USER")
                .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                .requestMatchers("/myCards").hasRole("USER")
                .requestMatchers("/myLoans").hasRole("USER")
                .requestMatchers("/user-details").authenticated()
                        .requestMatchers("/contact","/register","/notices").permitAll()
                        .and().formLogin()
                        .and().httpBasic();
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
