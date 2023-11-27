package com.myserv.api.rh.services;

import com.myserv.api.rh.model.Item;
import com.myserv.api.rh.model.MenuItem;
import com.myserv.api.rh.model.SubItems;
import com.myserv.api.rh.repository.ItemRepository;
import com.myserv.api.rh.repository.MenuItemRepository;
import com.myserv.api.rh.repository.SubItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository menuItemRepository;
    @Autowired
    private SubItemsRepository subItemsRepository;
    @Autowired
    private ItemRepository itemRepository;
    public MenuItem save(MenuItem menuItem){
        if (menuItem.getSubItems() != null) {
            List<Item> items=menuItem.getSubItems();
            List<Item> savedItems = itemRepository.saveAll(items);

            for (Item savedItem : savedItems) {
                if (savedItem.getSubItems() != null && !savedItem.getSubItems().isEmpty()) {
                    List<SubItems> savedSubItems =subItemsRepository.saveAll(savedItem.getSubItems());
                    savedItem.setSubItems(savedSubItems);
                }
            }

            menuItem.setSubItems(savedItems);

        }


        return menuItemRepository.save(menuItem);
    }


}
