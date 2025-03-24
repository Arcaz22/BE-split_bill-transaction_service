package com.splitbill.transaction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class SwaggerConfig {

    @Value("${info.app.name}")
    private String serviceName;

    @Value("${info.app.desc}")
    private String serviceDesc;

    @Value("${app.doc-info.url-dev}")
    private String devUrl;

    @Value("${app.doc-info.url-prod}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        // Configure servers
        List<Server> servers = new ArrayList<>();
        servers.add(new Server()
            .url(devUrl)
            .description("Development Server"));
        servers.add(new Server()
            .url(prodUrl)
            .description("Production Server"));

        // Configure contact
        Contact contact = new Contact()
            .name("Transaction Service Team")
            .email("support@example.com");

        // Configure API info
        Info info = new Info()
            .title(serviceName)
            .description(serviceDesc)
            .version("1.0")
            .contact(contact);

        // Configure security
        Components components = new Components()
            .addSecuritySchemes("bearer-jwt",
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .in(SecurityScheme.In.HEADER)
                    .name("Authorization"));

        SecurityRequirement securityRequirement = new SecurityRequirement()
            .addList("bearer-jwt", Arrays.asList("read", "write"));

        return new OpenAPI()
            .info(info)
            .servers(servers)
            .components(components)
            .addSecurityItem(securityRequirement);
    }
}
