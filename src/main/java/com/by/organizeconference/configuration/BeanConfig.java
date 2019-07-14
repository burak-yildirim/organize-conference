package com.by.organizeconference.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author burakY
 */
@Configuration
public class BeanConfig {
    
    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper mapper = new ModelMapper();
        
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        
        return mapper;
    }
    
}
