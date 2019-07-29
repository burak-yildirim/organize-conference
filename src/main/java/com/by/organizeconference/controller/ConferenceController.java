package com.by.organizeconference.controller;

import com.by.organizeconference.dto.ConferenceDTO;
import com.by.organizeconference.entity.Conference;
import static com.by.organizeconference.utility.Piper.pipe;
import com.by.organizeconference.service.ConferenceService;
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
public class ConferenceController {
    
    @Autowired
    private ConferenceService conferenceService;
    
    @Autowired
    private Mapper<Conference, ConferenceDTO> conferenceEDMapper;
    
    @Autowired
    private Mapper<ConferenceDTO, Conference> conferenceDEMapper;
    
    @Autowired
    private Merger merger;

    
    @GetMapping("/conferences")
    public List<ConferenceDTO> findAll(){
        return conferenceService.findAll().stream()
                .map(conferenceEDMapper::map).collect(toList());
    }
    
    @GetMapping("/conferences/{id}")
    public ConferenceDTO findById(@PathVariable Long id){
        return conferenceEDMapper.map(conferenceService.findById(id));
    }
    
    @PostMapping("/conferences")
    public ConferenceDTO save(@RequestBody ConferenceDTO conferenceDTO){
        conferenceDTO.setId(null);
        
        return update(conferenceDTO);
    }
    
    @PutMapping("/conferences")
    public ConferenceDTO update(@RequestBody ConferenceDTO conferenceDTO){

        return (ConferenceDTO) pipe(
                dto -> conferenceDEMapper.map((ConferenceDTO) dto), 
                e -> conferenceService.save((Conference) e), 
                e -> conferenceEDMapper.map((Conference) e)
        ).apply(conferenceDTO);
    }
    
    @PatchMapping("/conferences/{id}")
    public ConferenceDTO partialUpdate(@RequestBody ConferenceDTO conferenceDTO, @PathVariable Long id){
        conferenceDTO.setId(null); // ignore if an id is given in the request body
        conferenceDTO = merger.merge(conferenceDTO, this.findById(id));
        
        return this.update(conferenceDTO);
    }
    
    @DeleteMapping("/conferences/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return conferenceService.deleteById(id);
    }
}
