package com.by.organizeconference.service;

import com.by.organizeconference.entity.Speaker;
import java.util.List;

/**
 *
 * @author burakY
 */
public interface SpeakerService {
    
    public List<Speaker> findAll();
    
    public Speaker findById(Long id);
    
    public void save(Speaker speaker);
    
    public void deleteById(Long id);
    
}
