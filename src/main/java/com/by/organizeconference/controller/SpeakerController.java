package com.by.organizeconference.controller;

import com.by.organizeconference.entity.Speaker;
import com.by.organizeconference.service.SpeakerService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author burakY
 */
@RequestMapping("/api")
@RestController
public class SpeakerController {
    
    @Autowired
    private SpeakerService speakerService;

    @GetMapping("/speakers")
    public List<Speaker> findAll(){
        return speakerService.findAll();
    }
    
    @GetMapping("/speakers/{id}")
    public Speaker findById(@PathVariable Long id){
        return speakerService.findById(id);
    }
    
    @PostMapping("/speakers")
    public Speaker save(Speaker speaker){
        speaker.setId(null);
        return speakerService.save(speaker);
    }
    
    @PutMapping("/speakers")
    public Speaker update(Speaker speaker){
        return speakerService.save(speaker);
    }
    
    @DeleteMapping("/speakers/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return speakerService.deleteById(id);
    }
    
}
