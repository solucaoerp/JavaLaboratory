package com.ibrplanner.logistica.settings;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;

import java.util.List;

/**
 * Classe responsável por configurar os conversores de mensagens HTTP usados pelo Spring.
 * Essa classe adiciona o conversor MappingJackson2XmlHttpMessageConverter à lista de conversores usados pelo Spring
 * para converter objetos em formatos de mensagem HTTP. Com esse conversor, o Spring será capaz de converter objetos
 * java.util.Arrays$ArrayList em XML.
 * Para usar essa classe, basta instanciar um objeto de WebConfig e chamar o método configureMessageConverters,
 * passando a lista de conversores que você deseja configurar.
 */
public class WebConfig {

    /**
     * Método responsável por configurar os conversores de mensagens HTTP usados pelo Spring.
     *
     * @param converters Lista de conversores usados pelo Spring para converter objetos em formatos de mensagem HTTP.
     *                   Essa lista será atualizada com o conversor MappingJackson2XmlHttpMessageConverter.
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2XmlHttpMessageConverter());
    }
}
