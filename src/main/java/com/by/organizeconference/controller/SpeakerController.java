package com.by.organizeconference.controller;

import com.by.organizeconference.dto.SpeakerDTO;
import com.by.organizeconference.entity.Speaker;
import com.by.organizeconference.service.SpeakerService;
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
public class SpeakerController {
    
    @Autowired
    private SpeakerService speakerService;
    
    @Autowired 
    private ModelMapper modelMapper;
    
    private SpeakerDTO speakerEntityToDTO(Speaker speaker){
        return modelMapper.map(speaker, SpeakerDTO.class);
    }
    
    private Speaker speakerDTOToEntity(SpeakerDTO speakerDTO){
        return modelMapper.map(speakerDTO, Speaker.class);
    }

    @GetMapping("/speakers")
    public List<SpeakerDTO> findAll(){
        return speakerService.findAll().stream().map(this::speakerEntityToDTO).collect(toList());
    }
    
    @GetMapping("/speakers/{id}")
    public SpeakerDTO findById(@PathVariable Long id){
        return speakerEntityToDTO(speakerService.findById(id));
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
