package com.julioluis.singlesecurityapp.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    private Long id;
    @Column(name = "email")
    private String username;
    @Column(name = "pwd")
    private String password;
    private String role;

}
