package com.splitbill.transaction.config.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import com.splitbill.transaction.utils.constant.ResourceServerConstant;

@Component
public class CorsConfig {

    CorsConfiguration corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOriginPatterns(List.of("*"));
        corsConfig.setAllowedHeaders(Arrays.asList(ResourceServerConstant.HTTP_HEADER_ALLOWED));
        corsConfig.setAllowedOrigins(Arrays.asList(ResourceServerConstant.HTTP_ORIGIN_ALLOWED));
        corsConfig.setAllowedMethods(Arrays.asList(ResourceServerConstant.HTTP_METHOD_ALLOWED));
        corsConfig.setAllowCredentials(true);
        corsConfig.setExposedHeaders(Arrays.asList(
            HttpHeaders.AUTHORIZATION,
            HttpHeaders.CONTENT_DISPOSITION,
            "X-User-ID"
        ));
        return corsConfig;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration());
        return source;
    }
}
