package br.com.orientec.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrientecBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrientecBackendApplication.class, args);
    }
}
