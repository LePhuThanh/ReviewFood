package com.project.reviewfood.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/admin")
@CrossOrigin("*") // Permit all domain access
public class AdminController {
    @GetMapping(value = "/")
    public String helloAdminController(){
        return "Admin access level";
    }
}


