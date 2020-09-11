package com.geekbrains.krilov.context.app;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private long id;
    private static long counter = 0;
    private List<Item> itemList = new ArrayList<>();
    private ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        this.id = counter++;
        System.out.println("Created new Cart id: " + id);
        this.productRepository = productRepository;
    }

    public void addToCart(Long itemId) {
        try {
            itemList.add(productRepository.findItemById(itemId));
            System.out.println("Item added to Cart");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteFromCart(Long itemId) {
        try {
            itemList.remove(productRepository.findItemById(itemId));
            System.out.println("Item removed from your Cart");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Item> getCartList() {
        return itemList;
    }
}
