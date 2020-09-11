package com.geekbrains.krilov.context.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);

        new Thread(() -> {
            try {
                productService.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        context.close();
    }
}
