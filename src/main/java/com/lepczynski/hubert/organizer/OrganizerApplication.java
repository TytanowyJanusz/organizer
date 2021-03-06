package com.lepczynski.hubert.organizer;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class OrganizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizerApplication.class, args);
	}

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("PUT", "DELETE","GET","POST")
                        //.allowedHeaders("header1", "header2", "header3")
                       // .exposedHeaders("header1", "header2")
                        .allowCredentials(true).maxAge(3600);
            }
        };
    }
}
