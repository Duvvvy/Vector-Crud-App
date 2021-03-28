package com.ryanbircham.vectorcrudapp.dto;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean isValidUserData() {
        if(getFirstName().isBlank()){
            return false;
        }
        if(getEmailAddress().isBlank()){
            return false;
        }
        if(getPassword().isBlank()){
            return false;
        }
        if(getLastName().isBlank()){
            return false;
        }
        return true;
    }

}

