package com.ibrplanner.pedidos.config;

import com.ibrplanner.pedidos.services.DBServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class ProfileDevConfig {

    @Autowired
    private DBServiceTest dbServiceTest;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbServiceTest.instantiateTestDatabase();
        return true;
    }
}
