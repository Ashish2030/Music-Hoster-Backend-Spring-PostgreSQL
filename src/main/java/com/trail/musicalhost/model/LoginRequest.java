package com.trail.musicalhost.model;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private String username;
    private String password;

    // Constructors
    // default constructor for json parsing
    public LoginRequest(){

    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
