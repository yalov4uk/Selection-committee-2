package com.yalov4uk.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by valera on 5/26/17.
 */
@Configuration
public class TestConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
