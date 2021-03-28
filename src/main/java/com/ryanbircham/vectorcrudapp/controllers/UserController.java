package com.ryanbircham.vectorcrudapp.controllers;

import com.ryanbircham.vectorcrudapp.dto.User;
import com.ryanbircham.vectorcrudapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "{email_address}")
    public ResponseEntity<Object> getUser(@PathVariable String email_address){
        Optional<User> userInDB = userRepository.findUserByEmailAddress(email_address);
        if(userInDB.isPresent()){
            return new ResponseEntity<Object>(userInDB.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity("Could not find a user with that email address.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "{email_address}")
    public ResponseEntity<Object> deleteUser(@PathVariable String email_address){
        Optional<User> outgoingUser = userRepository.findUserByEmailAddress(email_address);
        if(outgoingUser.isPresent()){
            userRepository.delete(outgoingUser.get());
            return new ResponseEntity("User Deleted", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("Could not find a user with that email address.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User newUser) {
        try{
            if (newUser.isValidUserData()) {
                userRepository.save(newUser);
                Optional<User> savedUser = userRepository.findUserByEmailAddress(newUser.getEmailAddress());
                if(savedUser.isPresent()){
                    return new ResponseEntity("User added", HttpStatus.OK);
                }else{
                    return new ResponseEntity("Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity("One or more fields are blank", HttpStatus.BAD_REQUEST);
            }

        } catch(DataIntegrityViolationException e) {
            return new ResponseEntity("User with that email already exists", HttpStatus.CONFLICT);
        }

    }
}
