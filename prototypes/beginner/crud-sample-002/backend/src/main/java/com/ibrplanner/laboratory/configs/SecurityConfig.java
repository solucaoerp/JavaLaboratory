/**
 * A classe SecurityConfig é responsável por configurar a segurança da aplicação utilizando o Spring Security.
 * Ela define as configurações de segurança para diferentes URLs e recursos da aplicação, gerenciando a autenticação e autorização.
 * Para configurar a segurança, a classe utiliza as anotações @Configuration e @EnableWebSecurity.
 * Também utiliza a classe HttpSecurity para definir as regras de segurança e a classe CorsConfigurationSource para configurar o CORS (Cross-Origin Resource Sharing).
 * A classe define dois métodos @Bean: filterChain e corsConfigurationSource.
 * O método filterChain configura o filtro de segurança e define as regras de segurança para diferentes URLs da aplicação.
 * O método corsConfigurationSource configura as políticas de CORS para a aplicação, permitindo que ela seja acessada de diferentes origens.
 */
package com.ibrplanner.laboratory.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Este método @Bean retorna uma instância de SecurityFilterChain configurada com as regras de segurança da aplicação.
     * Ele utiliza a classe HttpSecurity para definir as regras de segurança para diferentes URLs e recursos da aplicação.
     * Define que o frameOptions devem ser desabilitados, que o CORS e o CSRF devem ser desabilitados,
     * que a criação de sessão deve ser STATELESS e que todas as requisições devem ser permitidas.
     *
     * @param http a instância de HttpSecurity utilizada para configurar as regras de segurança.
     * @return uma instância de SecurityFilterChain configurada com as regras de segurança da aplicação.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests((auth) -> auth.anyRequest().permitAll());

        return http.build();
    }

    /**
     * Este método @Bean retorna uma instância de CorsConfigurationSource configurada com as políticas de CORS da aplicação.
     * Ele define as políticas de CORS permitindo que a aplicação seja acessada de diferentes origens,
     * permitindo que sejam utilizados os métodos POST, GET, PUT, DELETE e OPTIONS.
     *
     * @return uma instância de CorsConfigurationSource configurada com as políticas de CORS da aplicação.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
