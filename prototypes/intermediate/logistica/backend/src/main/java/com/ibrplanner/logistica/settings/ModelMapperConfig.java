package com.ibrplanner.logistica.settings;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração que cria uma instância do ModelMapper.
 * O ModelMapper é uma biblioteca Java para mapear objetos DTO (Data Transfer Object) para modelos de domínio e vice-versa.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Método que cria uma instância do ModelMapper e a registra no contexto do Spring como um Bean.
     *
     * @return Instância do ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
