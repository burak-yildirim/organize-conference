package com.by.organizeconference.controller;

import com.by.organizeconference.dto.DetailedSpeakerDTO;
import com.by.organizeconference.dto.SpeakerDTO;
import com.by.organizeconference.entity.Speaker;
import com.by.organizeconference.service.SpeakerService;
import com.by.organizeconference.utility.Merger;
import static com.by.organizeconference.utility.Piper.pipe;
import com.remondis.remap.Mapper;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class SpeakerController {
    
    @Autowired
    private SpeakerService speakerService;
    
    @Autowired
    private Merger merger;
    
    @Autowired
    private Mapper<Speaker, SpeakerDTO> speakerEDMapper;
    
    @Autowired
    private Mapper<Speaker, DetailedSpeakerDTO> detailedSpeakerEDMapper;
    
    @Autowired
    private Mapper<DetailedSpeakerDTO, Speaker> detailedSpeakerDEMapper;

    @GetMapping("/speakers")
    public List<SpeakerDTO> findAll(){
        return speakerService.findAll().stream().map(speakerEDMapper::map).collect(toList());
    }
    
    @GetMapping("/speakers/{id}")
    public DetailedSpeakerDTO findById(@PathVariable Long id){
        return detailedSpeakerEDMapper.map(speakerService.findById(id));
    }
    
    @PostMapping("/speakers")
    public DetailedSpeakerDTO save(@RequestBody DetailedSpeakerDTO detailedSpeaker){
        detailedSpeaker.setId(null);
        return update(detailedSpeaker);
    }
    
    @PutMapping("/speakers")
    public DetailedSpeakerDTO update(@RequestBody DetailedSpeakerDTO detailedSpeaker){
        return (DetailedSpeakerDTO) pipe(
                dto -> detailedSpeakerDEMapper.map((DetailedSpeakerDTO) dto),
                e -> speakerService.save((Speaker) e),
                e -> detailedSpeakerEDMapper.map((Speaker) e)
        ).apply(detailedSpeaker);
    }
    
    @PatchMapping("/speakers/{id}")
    public DetailedSpeakerDTO partialUpdate(@RequestBody DetailedSpeakerDTO detailedSpeaker, @PathVariable Long id){
        detailedSpeaker.setId(null);
        detailedSpeaker = merger.merge(detailedSpeaker, findById(id));
        
        return update(detailedSpeaker);
    }
    
    @DeleteMapping("/speakers/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return speakerService.deleteById(id);
    }
    
}
