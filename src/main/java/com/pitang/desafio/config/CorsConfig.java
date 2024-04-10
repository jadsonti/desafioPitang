package com.pitang.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Permitir credenciais
        config.addAllowedOrigin("http://localhost:4200"); // Adicione as origens permitidas explicitamente aqui
        // Para permitir múltiplas origens específicas, repita a linha acima para cada origem
        // ou use config.setAllowedOriginPatterns(Arrays.asList("http://localhost:3000", "https://meusite.com"));
        config.addAllowedHeader("*"); // Permitir todos os headers
        config.addAllowedMethod("*"); // Permitir todos os métodos (GET, POST, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplicar configuração de CORS para todas as rotas

        return new CorsFilter(source);
    }
}