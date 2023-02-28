package com.julioluis.singlesecurityapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("finantials")
public class FinantialController {

    @GetMapping
    public String getAllDonation() {
        return "All Donation";
    }
}
