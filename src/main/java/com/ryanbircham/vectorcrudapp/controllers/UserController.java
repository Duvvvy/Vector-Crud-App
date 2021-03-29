package com.ryanbircham.vectorcrudapp.controllers;

import com.ryanbircham.vectorcrudapp.dto.User;
import com.ryanbircham.vectorcrudapp.handler.JsonResponseEntity;
import com.ryanbircham.vectorcrudapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        if(userInDB.isPresent()){//This one should be a response entity as it passes back its own User Object.
            return new ResponseEntity<>(userInDB.get(), HttpStatus.OK);
        } else {
            return new JsonResponseEntity(
                    "Could not find a user with that email address.",
                    HttpStatus.NOT_FOUND
            ).getResponse();
        }
    }

    @DeleteMapping(value = "{email_address}")
    public ResponseEntity<Object> deleteUser(@PathVariable String email_address){
        Optional<User> outgoingUser = userRepository.findUserByEmailAddress(email_address);
        if(outgoingUser.isPresent()){
            userRepository.delete(outgoingUser.get());
            return new JsonResponseEntity("User Deleted", HttpStatus.OK).getResponse();
        } else {
            return new JsonResponseEntity(
                    "Could not find a user with that email address.",
                    HttpStatus.NOT_FOUND
            ).getResponse();
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User newUser) {
        try{
            if (newUser.isValidUserData()) {
                userRepository.save(newUser);
                Optional<User> savedUser = userRepository.findUserByEmailAddress(newUser.getEmailAddress());
                if(savedUser.isPresent()){
                    return new JsonResponseEntity(
                            "The user was created successfully.",
                            HttpStatus.CREATED
                    ).getResponse();

                }else{
                    return new JsonResponseEntity(
                            "Unexpected error",
                            HttpStatus.INTERNAL_SERVER_ERROR
                    ).getResponse();
                }
            } else {
                return new JsonResponseEntity(
                        "One or more fields are blank",
                        HttpStatus.BAD_REQUEST
                ).getResponse();
            }

        } catch(DataIntegrityViolationException e) {
            return new JsonResponseEntity(
                    "User with that email already exists",
                    HttpStatus.CONFLICT
            ).getResponse();
        }

    }

    @PutMapping(value = "{email_address}")
    public ResponseEntity<Object> updateUser(@PathVariable String email_address, @RequestBody User update){
        Optional<User> savedUser = userRepository.findUserByEmailAddress(email_address);
        if(savedUser.isPresent()){
            User userInDB = savedUser.get();
            //We set all fields, except ID, as when you save with a non-unique primary key, it will update.
            userInDB.setEmailAddress(update.getEmailAddress());
            userInDB.setPassword(update.getPassword());
            userInDB.setFirstName(update.getFirstName());
            userInDB.setLastName(update.getLastName());

            userRepository.save(userInDB);
            return new JsonResponseEntity("User updated", HttpStatus.OK).getResponse();

        }else{
            return new JsonResponseEntity("Error", HttpStatus.CONFLICT).getResponse();

        }
    }
}
