package com.by.organizeconference.service;

import com.by.organizeconference.entity.Speaker;
import java.util.List;
import java.util.Map;

/**
 *
 * @author burakY
 */
public interface SpeakerService {
    
    public List<Speaker> findAll();
    
    public Speaker findById(Long id);
    
    public Speaker save(Speaker speaker);
    
    public Map<String, Long> deleteById(Long id);
    
}
