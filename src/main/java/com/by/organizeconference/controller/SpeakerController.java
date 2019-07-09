package com.by.organizeconference.controller;

import com.by.organizeconference.entity.Speaker;
import com.by.organizeconference.service.SpeakerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author burakY
 */
@RequestMapping("/api")
@Controller
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
    
}
