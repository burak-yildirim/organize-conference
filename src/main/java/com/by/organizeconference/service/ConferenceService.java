package com.by.organizeconference.service;

import com.by.organizeconference.entity.Conference;
import java.util.List;
import java.util.Map;

/**
 *
 * @author supercoder
 */
public interface ConferenceService {
    
    public List<Conference> findAll();
    
    public Conference findById(Long id);
    
    public Conference save(Conference conference);
    
    public Map<String, Long> deleteById(Long id);
    
}
