package com.geekbrains.krilov.context.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ProductService {

    private final String GREETINGS_TEXT = "type /help to show available commands";
    private final String EXIT_COMMAND = "/end";
    private final String PRODUCT_LIST_COMMAND = "/products";
    private final String CART_LIST_COMMAND = "/cart";
    private final String PUT_TO_CART_COMMAND = "/put";
    private final String DELETE_FROM_CART_COMMAND = "/delete";
    private final String NEW_CART_COMMAND = "/new";
    private final String HELP_COMMAND = "/help";
    private final String NO_SUCH_COMMAND = "no such command";

    private Cart cart;
    private ProductRepository productRepository;

    @PostConstruct
    private void init() {
        this.cart = new Cart(productRepository);
        System.out.println(GREETINGS_TEXT);
    }

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void run() throws IOException {
        
        while (true) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String command = reader.readLine();
            
            if (command.startsWith(EXIT_COMMAND)) {
                System.exit(0);
            }
            
            if (command.startsWith(PRODUCT_LIST_COMMAND)) {
                getProductList();
                continue;
            }
            if (command.startsWith(PUT_TO_CART_COMMAND)) {
                putItemInCart(command);
                continue;
            }
            if (command.startsWith(DELETE_FROM_CART_COMMAND)) {
                deleteItemFromCart(command);
                continue;
            }
            if (command.startsWith(CART_LIST_COMMAND)) {
                getCartList();
                continue;
            }
            if (command.startsWith(NEW_CART_COMMAND)) {
                getNewCart();
                continue;
            }
            if (command.startsWith(HELP_COMMAND)) {
                typeCommandList();
                continue;
            }
            System.out.println(NO_SUCH_COMMAND);
        }
    }

    private void getNewCart() {
        this.cart = new Cart(productRepository);
    }

    private void getCartList() {
        System.out.println(cart.getCartList());
    }

    private void deleteItemFromCart(String command) {
        try {
            long itemId = Long.parseLong(command.split(" ")[1]);
            cart.deleteFromCart(itemId);
        } catch (Exception e) {
            System.out.println("wrong ID");
        }
    }

    private void putItemInCart(String command) {
        try {
            long itemId = Long.parseLong(command.split(" ")[1]);
            cart.addToCart(itemId);
        } catch (Exception e) {
            System.out.println("wrong ID");
        }
    }

    private void getProductList() {
        System.out.println(productRepository.getAllItems());
    }

    private void typeCommandList() {
        System.out.println(String.format("%s - shows this list \n" +
                "%s - shows list of available products to buy \n" +
                "%s - shows list of products in the current cart" +
                "%s id - put item with id in your cart \n" +
                "%s id - removes item with id from your cart \n" +
                "%s - creates new cart \n" +
                "%s - to close app", HELP_COMMAND, PRODUCT_LIST_COMMAND, CART_LIST_COMMAND, PUT_TO_CART_COMMAND, DELETE_FROM_CART_COMMAND, NEW_CART_COMMAND, EXIT_COMMAND));
    }

}
