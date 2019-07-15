package com.by.organizeconference.controller;

import com.by.organizeconference.dto.ConferenceDTO;
import com.by.organizeconference.entity.Conference;
import com.by.organizeconference.service.ConferenceService;
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
public class ConferenceController {
    
    @Autowired
    private ConferenceService conferenceService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    private ConferenceDTO conferenceEntityToDTO(Conference conference){
        return modelMapper.map(conference, ConferenceDTO.class);
    }
    
    private Conference conferenceDTOToEntity(ConferenceDTO conferenceDTO){
        return modelMapper.map(conferenceDTO, Conference.class);
    }
    
    @GetMapping("/conferences")
    public List<ConferenceDTO> findAll(){
        return conferenceService.findAll().stream().map(this::conferenceEntityToDTO).collect(toList());
    }
    
    @GetMapping("/conferences/{id}")
    public ConferenceDTO findById(@PathVariable Long id){
        return conferenceEntityToDTO(conferenceService.findById(id));
    }
    
    @PostMapping("/conferences")
    public ConferenceDTO save(@RequestBody ConferenceDTO conferenceDTO){
        conferenceDTO.setId(null);
        
        Conference conference = conferenceDTOToEntity(conferenceDTO);
        conference = conferenceService.save(conference);
        
        return conferenceEntityToDTO(conference);
    }
    
    @PutMapping("/conferences")
    public ConferenceDTO update(@RequestBody ConferenceDTO conferenceDTO){
        Conference conference = conferenceDTOToEntity(conferenceDTO);
        conference = conferenceService.save(conference);
        
        return conferenceEntityToDTO(conference);
    }
    
    @DeleteMapping("/conferences/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return conferenceService.deleteById(id);
    }
}
