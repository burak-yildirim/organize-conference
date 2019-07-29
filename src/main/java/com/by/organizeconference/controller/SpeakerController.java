package com.by.organizeconference.controller;

import com.by.organizeconference.dto.SpeakerDTO;
import com.by.organizeconference.entity.Speaker;
import com.by.organizeconference.service.SpeakerService;
import com.by.organizeconference.utility.Merger;
import com.remondis.remap.Mapper;
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
public class SpeakerController {
    
    @Autowired
    private SpeakerService speakerService;
    
    @Autowired
    private Merger merger;
    
    @Autowired
    private Mapper<Speaker, SpeakerDTO> speakerEDMapper;
    
    @Autowired
    private Mapper<SpeakerDTO, Speaker> speakerDEMapper;

    @GetMapping("/speakers")
    public List<SpeakerDTO> findAll(){
        return speakerService.findAll().stream().map(speakerEDMapper::map).collect(toList());
    }
    
    @GetMapping("/speakers/{id}")
    public SpeakerDTO findById(@PathVariable Long id){
        return speakerEDMapper.map(speakerService.findById(id));
    }
    
    @PostMapping("/speakers")
    public Speaker save(@RequestBody Speaker speaker){
        speaker.setId(null);
        return speakerService.save(speaker);
    }
    
    @PutMapping("/speakers")
    public Speaker update(@RequestBody Speaker speaker){
        return speakerService.save(speaker);
    }
    
    @DeleteMapping("/speakers/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return speakerService.deleteById(id);
    }
    
}
