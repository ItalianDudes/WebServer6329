package it.italiandudes.webserver6329.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EntityScan(basePackages = "it.italiandudes.webserver6329")
@ComponentScan(basePackages = "it.italiandudes.webserver6329")
@EnableJpaRepositories(basePackages = "it.italiandudes.webserver6329")
@EnableScheduling
public class WebServer6329 {

    // Spring Application Starter
    public static void main(String[] args) {
        SpringApplication.run(WebServer6329.class, args);
    }
}
