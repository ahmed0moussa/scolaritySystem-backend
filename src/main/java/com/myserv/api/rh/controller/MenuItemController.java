package com.myserv.api.rh.controller;

import com.myserv.api.rh.model.MenuItem;
import com.myserv.api.rh.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;
    @PostMapping(value = "/api/v1/menuItem/create" )
    public MenuItem place(@RequestBody MenuItem menuItem) throws IOException {

        return menuItemService.save(menuItem);
    }
}
