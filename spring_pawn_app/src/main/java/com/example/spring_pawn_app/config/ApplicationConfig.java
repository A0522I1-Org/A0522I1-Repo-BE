package com.example.spring_pawn_app.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Trần Thế Huy
 * @version 1
 * @since 28/5/2023
 */

@Configuration
public class ApplicationConfig {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("error");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
