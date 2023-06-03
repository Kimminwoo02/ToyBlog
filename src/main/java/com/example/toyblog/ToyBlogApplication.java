package com.example.toyblog;

import com.example.toyblog.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(AppConfig.class)
@EnableJpaAuditing
@SpringBootApplication
public class ToyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyBlogApplication.class, args);
    }

}
