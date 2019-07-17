package com.by.organizeconference.controller;

import com.by.organizeconference.converter.Converter;
import com.by.organizeconference.dto.ConferenceDTO;
import com.by.organizeconference.entity.Conference;
import static com.by.organizeconference.helper.Piper.pipe;
import com.by.organizeconference.service.ConferenceService;
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
public class ConferenceController {
    
    @Autowired
    private ConferenceService conferenceService;
    
    @Autowired
    private Converter<Conference, ConferenceDTO> converter;
    
    
    @GetMapping("/conferences")
    public List<ConferenceDTO> findAll(){
        return conferenceService.findAll().stream()
                .map(converter.entityToDto(ConferenceDTO.class)).collect(toList());
    }
    
    @GetMapping("/conferences/{id}")
    public ConferenceDTO findById(@PathVariable Long id){
        return converter.entityToDto(ConferenceDTO.class).apply(conferenceService.findById(id));
//        return converter.entityToDto(ConferenceDTO.class).apply(new String()); // this gives error, but shouldnt  
    }
    
    @PostMapping("/conferences")
    public ConferenceDTO save(@RequestBody ConferenceDTO conferenceDTO){
        conferenceDTO.setId(null);
        
        return (ConferenceDTO) pipe(
                converter.dtoToEntity(Conference.class), 
                e -> conferenceService.save((Conference) e), 
                converter.entityToDto(ConferenceDTO.class)
        ).apply(conferenceDTO);
    }
    
    @PutMapping("/conferences")
    public ConferenceDTO update(@RequestBody ConferenceDTO conferenceDTO){

        return (ConferenceDTO) pipe(
                converter.dtoToEntity(Conference.class),
                e -> conferenceService.save((Conference) e),
                converter.entityToDto(ConferenceDTO.class)
        ).apply(conferenceDTO);
    }
    
    @DeleteMapping("/conferences/{id}")
    public Map<String, Long> deleteById(@PathVariable Long id){
        return conferenceService.deleteById(id);
    }
}
