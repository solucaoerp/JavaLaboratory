package com.ibrplanner.logistica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LogisticaApiApplication {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LogisticaApiApplication.class, args);
    }

}
