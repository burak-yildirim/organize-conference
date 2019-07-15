package com.by.organizeconference.controller;

import com.by.organizeconference.dto.TopicDTO;
import com.by.organizeconference.entity.Topic;
import com.by.organizeconference.service.TopicService;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
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
    
    @Autowired
    private ModelMapper modelMapper;
    
    private TopicDTO topicEntityToDTO(Topic topic){
        return modelMapper.map(topic, TopicDTO.class);
    }
    
    private Topic topicDTOToEntity(TopicDTO topicDTO){
        return modelMapper.map(topicDTO, Topic.class);
    }
    
    @GetMapping("/topics")
    public List<TopicDTO> findAll(){
        return topicService.findAll().stream().map(this::topicEntityToDTO).collect(toList());
    }
    
    @GetMapping("/topicEntities")
    public List<Topic> findAllEntity(){
        return topicService.findAll();
    }
    
    @GetMapping("/topics/{id}")
    public TopicDTO findById(@PathVariable Long id){
        return topicEntityToDTO(topicService.findById(id));
    }
    
    @PostMapping("/topics")
    public TopicDTO save(@RequestBody TopicDTO topicDTO){
        topicDTO.setId(null);
        
        Topic topic = topicDTOToEntity(topicDTO);
        topic = topicService.save(topic);
        
        return topicEntityToDTO(topic);
    }
    
    @PutMapping("/topics")
    public TopicDTO update(@RequestBody TopicDTO topicDTO){
        Topic topic = topicDTOToEntity(topicDTO);
        topic = topicService.save(topic);
        
        return topicEntityToDTO(topic);
    }
    
    @DeleteMapping("/topics/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return topicService.deleteById(id);
    }
    
}
