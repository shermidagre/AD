package org.example;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("runner")
public class Runner {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Runner.class)
                .web(WebApplicationType.NONE)
                .profiles("runner")
                .run(args);
    }
}