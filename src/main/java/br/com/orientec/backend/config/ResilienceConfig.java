package br.com.orientec.backend.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ResilienceConfig {
    // Configurações declarativas ficam em application.yml.
    // Use @CircuitBreaker/@Retry apenas em chamadas externas,
    // nunca para mascarar falhas transacionais do PostgreSQL.
}
