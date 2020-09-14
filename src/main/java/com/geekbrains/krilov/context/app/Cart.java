package com.geekbrains.krilov.context.app;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private long id;
    private static long counter = 1;
    private List<Item> itemList = new ArrayList<>();
    private ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        this.id = counter++;
        System.out.println("Created new Cart (#" + id + ")" );
        this.productRepository = productRepository;
    }

    public void addToCart(Long itemId) throws ProductNotFoundException {
        itemList.add(productRepository.findItemById(itemId));
    }

    public void deleteFromCart(Long itemId) throws ProductNotFoundException {
        for (Item i : itemList) {
            if (i.getId() == itemId) {
                itemList.remove(i);
                return;
            }
        }
        throw new ProductNotFoundException("No such item in your Cart");
    }

    public List<Item> getCartList() {
        return itemList;
    }

    public double getCartSumm() {
        double summ = 0;
        for (Item item : itemList) {
            summ += item.getPrice();
        }
        return summ;
    }
}
