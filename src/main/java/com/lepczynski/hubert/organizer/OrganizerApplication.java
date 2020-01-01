package com.lepczynski.hubert.organizer;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.support.WebStack;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableHypermediaSupport(stacks = WebStack.WEBMVC, type = EnableHypermediaSupport.HypermediaType.HAL)
@SpringBootApplication
public class OrganizerApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(OrganizerApplication.class, args);
    }

    public class RestConfig implements RepositoryRestConfigurer
    {
        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig)
        {
            restConfig.setBasePath("organizer");
        }
    }
}
