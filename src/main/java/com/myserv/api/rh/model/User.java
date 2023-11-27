package com.myserv.api.rh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Document()
public class User {

    @Id
    private String id ;

    @NotBlank
    @Size(max = 20)
    @Indexed(name = "firstName")
    private String firstName;

    @NotBlank
    @Size(max = 20)
    @Indexed(name = "lastName")
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    @Indexed(name = "email")
    private String email;

    @JsonIgnore
    @NotBlank
    @Size(max = 120)
    @Indexed(name = "password")
    private String password;

    @DBRef
    private Set<Roles> roles = new HashSet<>();
    private List<MenuItem> menuItems ;

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public User(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(jsonString, User.class);
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.email = user.getEmail();
            this.password = user.getPassword();
            this.roles = user.getRoles();
        } catch (Exception e) {
            // Handle the exception (e.g., log the error, throw an exception, etc.)
            // Note: Catching a generic Exception is not recommended in production code. Be more specific if possible.
        }
    }

    public User(String id, String firstName,String lastName, String email, String password, Set<Roles> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User() {

    }

    public User(String firstName,String lastName, String email, String password, Set<Roles> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String firstName,String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
