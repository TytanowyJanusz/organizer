package com.lepczynski.hubert.organizer;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.support.WebStack;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableHypermediaSupport(stacks = WebStack.WEBMVC, type = EnableHypermediaSupport.HypermediaType.HAL)
@SpringBootApplication
public class OrganizerApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(OrganizerApplication.class, args);
    }


}
