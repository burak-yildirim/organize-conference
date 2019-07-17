package com.by.organizeconference.converter;

import java.util.function.Function;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 'E' for entity and 'D' for dto
 * @author burakY
 */
@Component
public class Converter<E, D> {
    
    @Autowired
    private ModelMapper modelMapper;
    
    /**
     * Returns a function which takes an entity and 
     * converts it to object of 'dtoClass' class
     * @param dtoClass - returned function will return an object of this class
     * @return 
     */
    public Function<E, D> entityToDto(Class<D> dtoClass){
        return entity -> modelMapper.map(entity, dtoClass);
    }
    
    /**
     * Returns a function which takes an dto and
     * converts it to object of 'entityClass' class
     * @param entityClass - returned function will return an object of this class
     * @return 
     */
    public  Function<D, E> dtoToEntity(Class<E> entityClass){
        return dto -> modelMapper.map(dto, entityClass);
    }

}
