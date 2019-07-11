package com.by.organizeconference.service;

import com.by.organizeconference.entity.Topic;
import java.util.List;
import java.util.Map;

/**
 *
 * @author supercoder
 */
public interface TopicService {
    
    public List<Topic> findAll();
    
    public Topic findById(Long id);
    
    public Topic save(Topic topic);
    
    public Map<String, Long> deleteById(Long id);
    
}
