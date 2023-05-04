package com.ibrplanner.pedidos.config;

import com.ibrplanner.pedidos.services.DBServiceTest;
import com.ibrplanner.pedidos.services.EmailService;
import com.ibrplanner.pedidos.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class ProfileTestConfig {

    @Autowired
    private DBServiceTest dbServiceTest;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbServiceTest.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }

}
