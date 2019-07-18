package com.by.organizeconference.controller;

import com.by.organizeconference.converter.Converter;
import com.by.organizeconference.dto.TopicDTO;
import com.by.organizeconference.entity.Topic;
import static com.by.organizeconference.helper.Piper.pipe;
import com.by.organizeconference.service.TopicService;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;
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
    private Converter<Topic, TopicDTO> converter;
    
    @GetMapping("/topics")
    public List<TopicDTO> findAll(){
        return topicService.findAll().stream()
                .map(converter.entityToDto(TopicDTO.class)).collect(toList());
    }
    
    @GetMapping("/topicEntities")
    public List<Topic> findAllEntity(){
        return topicService.findAll();
    }
    
    @GetMapping("/topics/{id}")
    public TopicDTO findById(@PathVariable Long id){
        return converter.entityToDto(TopicDTO.class).apply(topicService.findById(id));
    }
    
    @PostMapping("/topics")
    public TopicDTO save(@RequestBody TopicDTO topicDTO){
        topicDTO.setId(null);
        
        return (TopicDTO) pipe(
                converter.dtoToEntity(Topic.class),
                t -> topicService.save((Topic) t),
                converter.entityToDto(TopicDTO.class)
        ).apply(topicDTO);
    }
    
    @PutMapping("/topics")
    public TopicDTO update(@RequestBody TopicDTO topicDTO){
        return (TopicDTO) pipe(
                converter.dtoToEntity(Topic.class),
                t -> topicService.save((Topic) t),
                converter.entityToDto(TopicDTO.class)
        ).apply(topicDTO);
    }
    
    @DeleteMapping("/topics/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return topicService.deleteById(id);
    }
    
}
