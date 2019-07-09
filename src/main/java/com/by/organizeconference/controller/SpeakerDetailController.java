package com.by.organizeconference.controller;

import com.by.organizeconference.entity.SpeakerDetail;
import com.by.organizeconference.service.SpeakerDetailService;
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
public class SpeakerDetailController {
    
    @Autowired
    private SpeakerDetailService detailService;
    
    @GetMapping("/speakerDetails")
    public List<SpeakerDetail> findAll(){
        return detailService.findAll();
    }
    
    @GetMapping("/speakerDetails/{id}")
    public SpeakerDetail findById(@PathVariable Long id){
        return detailService.findById(id);
    }
    
    @PostMapping("/speakerDetails")
    public SpeakerDetail save(@RequestBody SpeakerDetail detail){
        detail.setId(null);
        return detailService.save(detail);
    }
    
    @PutMapping("/speakerDetails")
    public SpeakerDetail update(@RequestBody SpeakerDetail detail){
        return detailService.save(detail);
    }
    
    @DeleteMapping("/speakerDetails/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return detailService.deleteById(id);
    }

}
