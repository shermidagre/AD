package org.example.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String conMongo;

    @Bean
    public MongoClient mongoClient (){
        try {
            return MongoClients.create(conMongo);
        }catch (Exception e){
            throw new RuntimeException("Error conectando a mongo", e);
        }
    }
}
