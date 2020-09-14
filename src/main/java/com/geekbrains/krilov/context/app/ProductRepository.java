package com.geekbrains.krilov.context.app;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Item> itemList;

    @PostConstruct
    public void init() {
        this.itemList = new ArrayList<>(Arrays.asList(
                new Item(1L, "Bread", 25),
                new Item(2L, "Milk", 80),
                new Item(3L, "Cheese", 900),
                new Item(4L, "Eggs", 120),
                new Item(5L, "Coffee", 450)
        ));
        System.out.println("Trucks arrived and filled our shop with items");
    }

    public List<Item> getAllItems() {
        return Collections.unmodifiableList(itemList);
    }

    public Item findItemById(Long id) {
        for (Item i : itemList) {
            if (i.getId() == id) {
                return i;
            }
        }
        throw new ProductNotFoundException("Item not found on our store");
    }

    public void addNewItem(Item item) {
        itemList.add(item);
    }
}
