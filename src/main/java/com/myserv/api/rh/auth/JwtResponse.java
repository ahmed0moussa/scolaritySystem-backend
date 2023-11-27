package com.myserv.api.rh.auth;

import com.myserv.api.rh.model.MenuItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;
    private List<MenuItem> menuItems ;
    public JwtResponse(String token, String id, String email, List<String> roles, List<MenuItem> menuItems, String firstName, String lastName) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.menuItems=menuItems;
    }



}