package com.by.organizeconference.utility;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author burakY
 */
@Component
public class Merger {
    
    private Predicate<PropertyDescriptor> nulls(Object obj){
        return pd -> {
            try {
                Method getterMethod = pd.getReadMethod();
                return getterMethod.invoke(obj) == null;
            } catch (Exception e) {e.printStackTrace();}
            
            return true;
        };
    }
    
    /**
     * Copies non-null properties of source to target. Nested objects
     * are copied as they are, their properties are not checked.
     * @param <T>
     * @param source
     * @param target
     * @return 
     */
    public <T> T merge(T source, T target){
        try{
            String[] nullProperties = Arrays
                .stream(Introspector.getBeanInfo(source.getClass()).getPropertyDescriptors())
                .filter(nulls(source))
                .map(pd -> pd.getName()).toArray(String[]::new);
            BeanUtils.copyProperties(source, target, nullProperties);
        }catch(Exception e){e.printStackTrace();}
        return target;
    }
    
    
}
