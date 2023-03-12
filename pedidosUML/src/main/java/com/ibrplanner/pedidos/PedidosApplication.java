package com.ibrplanner.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
@PropertySource("classpath:env.properties")
public class PedidosApplication {

    public static void main(String[] args) {
        // SpringApplication.run(PedidosApplication.class);
        SpringApplication app = new SpringApplication(PedidosApplication.class);
        Environment env = app.run(args).getEnvironment();
        System.out.println(env.getProperty("spring.datasource.url")); /* Para debug (exibe a url Database) */
        app.setAdditionalProfiles(env.getProperty("spring.profiles.active"));
    }
}