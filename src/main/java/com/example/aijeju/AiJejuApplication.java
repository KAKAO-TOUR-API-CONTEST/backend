package com.example.aijeju;

import com.example.aijeju.config.WebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class AiJejuApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiJejuApplication.class, args);
	}

	@Bean
	public WebMvcConfig corsConfigurer() {
		return new WebMvcConfig() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
			}
		};
	}

}
