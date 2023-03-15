package com.ibrplanner.pedidos.config;

import com.ibrplanner.pedidos.services.DBServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class ProfileDevConfig {

    @Autowired
    private DBServiceTest dbServiceTest;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (!"create".equals(strategy)) {
            return false;
        }

        dbServiceTest.instantiateTestDatabase();
        return true;
    }
}
