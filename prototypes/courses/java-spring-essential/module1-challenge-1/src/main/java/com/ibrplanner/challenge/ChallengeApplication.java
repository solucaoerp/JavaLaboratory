package com.ibrplanner.challenge;

import com.ibrplanner.challenge.domain.Order;
import com.ibrplanner.challenge.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Order order = new Order(1034, 150.0,0.2);
        double total = orderService.total(order);

        System.out.println("Pedido código: " + order.getCode());
        System.out.printf("Valor total: %.2f", total);

    }
}
