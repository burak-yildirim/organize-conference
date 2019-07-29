package com.by.organizeconference.controller;

import com.by.organizeconference.dto.TopicDTO;
import com.by.organizeconference.entity.Topic;
import static com.by.organizeconference.utility.Piper.pipe;
import com.by.organizeconference.service.TopicService;
import com.by.organizeconference.utility.Merger;
import com.remondis.remap.Mapper;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    private Mapper<TopicDTO, Topic> topicDEMapper;
    
    @Autowired
    private Mapper<Topic, TopicDTO> topicEDMapper;
    
    @Autowired
    private Merger merger;

    
    @GetMapping("/topics")
    public List<TopicDTO> findAll(){
        return topicService.findAll().stream()
                .map(topicEDMapper::map).collect(toList());
    }
    
    @GetMapping("/topicEntities")
    public List<Topic> findAllEntity(){
        return topicService.findAll();
    }
    
    @GetMapping("/topics/{id}")
    public TopicDTO findById(@PathVariable Long id){
        return topicEDMapper.map(topicService.findById(id));
    }
    
    @PostMapping("/topics")
    public TopicDTO save(@RequestBody TopicDTO topicDTO){
        topicDTO.setId(null);
        
        return this.update(topicDTO);
    }
    
    @PutMapping("/topics")
    public TopicDTO update(@RequestBody TopicDTO topicDTO){
        return (TopicDTO) pipe(
                dto -> topicDEMapper.map((TopicDTO) dto),
                e -> topicService.save((Topic) e),
                e -> topicEDMapper.map((Topic) e)
        ).apply(topicDTO);
    }
    
    @PatchMapping("/topics/{id}")
    public TopicDTO partialUpdate(@RequestBody TopicDTO topicDTO, @PathVariable Long id){
        topicDTO.setId(null);
        topicDTO = merger.merge(topicDTO, this.findById(id));
        
        return this.update(topicDTO);
    }
    
    @DeleteMapping("/topics/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return topicService.deleteById(id);
    }
    
    
}
