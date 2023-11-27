package com.myserv.api.rh.services;

import com.myserv.api.rh.model.MenuItem;
import com.myserv.api.rh.model.RoleType;
import com.myserv.api.rh.model.Roles;
import com.myserv.api.rh.model.User;
import com.myserv.api.rh.repository.MenuItemRepository;
import com.myserv.api.rh.repository.RoleRepository;
import com.myserv.api.rh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  UserRepository repository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;
    public List<User> findAllUsers(){
        return userRepository.findAllUsersExceptAdmin("admin@admin.com");
    }
    public Optional<User> findById(String id){
        return userRepository.findById(id);
    }
    public void deleteById(String id){
        userRepository.deleteById(id);
    }

    public void createadmine() {

        User userAdmin = new User();
        User savedUser = null;

        if (!roleRepository.existsByName(RoleType.ROLE_ADMIN)) {
            Roles roleAdmin = new Roles();
            roleAdmin.setName(RoleType.ROLE_ADMIN);
            Roles dpt = roleRepository.save(roleAdmin);


        }
        if (!roleRepository.existsByName(RoleType.ROLE_USER)) {
            Roles roleAdmin = new Roles();
            roleAdmin.setName(RoleType.ROLE_USER);
            Roles dpt = roleRepository.save(roleAdmin);


        }

        if (!repository.existsByEmail("admin@admin.com")) {
            userAdmin.setEmail("admin@admin.com");
            userAdmin.setFirstName("admin");
            userAdmin.setLastName("admin");
            userAdmin.setPassword(new BCryptPasswordEncoder().encode("admin.123"));
            Set<Roles> roles = new HashSet<>();
            Roles userRole = roleRepository.findByName(RoleType.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            userAdmin.setRoles(roles);
            List<MenuItem> menuItemList=menuItemRepository.findAll();
            userAdmin.setMenuItems(menuItemList);


            savedUser=repository.save(userAdmin);
        }

    }
    public User updateUser(String id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            // Update the fields you want to change from updatedUser into existingUser
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            String newPassword = updatedUser.getPassword();
            if (newPassword != null && !newPassword.isEmpty()) {
                // Hash the new password before saving it
                existingUser.setPassword(passwordEncoder.encode(newPassword));
            }

        }
        return userRepository.save(existingUser);

    }
    public List<MenuItem> updateMenuUser(String id,List<MenuItem> menuItems){
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setMenuItems(menuItems);
            userRepository.save(existingUser);
        }
        return menuItems;
    }


}

