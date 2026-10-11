package br.com.orientec.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
public class OpenApiConfig {
    private static final String BEARER = "bearerAuth";

    /** Rotas liberadas no SecurityConfig (sem JWT). */
    private static final Set<String> ROTAS_PUBLICAS = Set.of(
            "/api/v1/ping",
            "/api/v1/auth/login",
            "/api/v1/stations/handshake",
            "/api/v1/stations/exchange",
            "/api/v1/stations/session",
            "/api/v1/stations/heartbeat");

    @Bean
    OpenAPI orientecOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Orientec Web API")
                        .version("V3")
                        .description("Backend do Orientec Web — núcleo inicial de acesso e OS."))
                .components(new Components().addSecuritySchemes(BEARER, new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
                .security(List.of(new SecurityRequirement().addList(BEARER)));
    }

    /** Remove o requisito de autenticação das rotas públicas na documentação. */
    @Bean
    OpenApiCustomizer rotasPublicasCustomizer() {
        return openApi -> {
            if (openApi.getPaths() == null) return;
            openApi.getPaths().forEach((path, item) -> {
                if (ROTAS_PUBLICAS.contains(path)) {
                    item.readOperations().forEach(op -> op.setSecurity(List.of()));
                }
            });
        };
    }
}
