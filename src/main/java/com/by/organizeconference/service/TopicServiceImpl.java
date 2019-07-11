package com.by.organizeconference.service;

import com.by.organizeconference.dao.TopicRepository;
import com.by.organizeconference.entity.Topic;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author burakY
 */
@Service
public class TopicServiceImpl implements TopicService{
    
    @Autowired
    private TopicRepository topicRepo;

    @Override
    public List<Topic> findAll() {
        return topicRepo.findAll();
    }

    @Override
    public Topic findById(Long id) {
        return topicRepo.findById(id).orElseGet(Topic::new);
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepo.save(topic);
    }

    @Override
    public Map<String, Long> deleteById(Long id) {
        topicRepo.deleteById(id);
        
        Map<String, Long> response = new HashMap<>();
        response.put("id", id);
        return response;
    }

}
