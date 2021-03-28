package com.ryanbircham.vectorcrudapp.controllers;

import com.ryanbircham.vectorcrudapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserRepository UserRepository;

    
}
