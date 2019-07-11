package com.by.organizeconference.controller;

import com.by.organizeconference.entity.Topic;
import com.by.organizeconference.service.TopicService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author burakY
 */
@RequestMapping("/api")
@RestController
public class TopicController {
    
    @Autowired
    private TopicService topicService;
    
    @GetMapping("/topics")
    public List<Topic> findAll(){
        return topicService.findAll();
    }
    
    @GetMapping("/topics/{id}")
    public Topic findById(@PathVariable Long id){
        return topicService.findById(id);
    }
    
    @PostMapping("/topics")
    public Topic save(@RequestBody Topic topic){
        topic.setId(null);
        return topicService.save(topic);
    }
    
    @PutMapping("/topics")
    public Topic update(@RequestBody Topic topic){
        return topicService.save(topic);
    }
    
    @DeleteMapping("/topics/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return topicService.deleteById(id);
    }
    
}
