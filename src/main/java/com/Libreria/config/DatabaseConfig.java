package com.Libreria.config;

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["Main.repositories"])
public class DatabaseConfig {
}
