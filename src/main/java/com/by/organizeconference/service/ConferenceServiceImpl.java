package com.by.organizeconference.service;

import com.by.organizeconference.dao.ConferenceRepository;
import com.by.organizeconference.entity.Conference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author burakY
 */
@Service
public class ConferenceServiceImpl implements ConferenceService{
    
    @Autowired
    private ConferenceRepository conferenceRepo;
    
    @Override
    public List<Conference> findAll() {
        return conferenceRepo.findAll();
    }

    @Override
    public Conference findById(Long id) {
        return conferenceRepo.findById(id).orElseGet(Conference::new);
    }

    @Override
    public Conference save(Conference conference) {
        return conferenceRepo.save(conference);
    }

    @Override
    public Map<String, Long> deleteById(Long id) {
        conferenceRepo.deleteById(id);
        
        Map<String, Long> response = new HashMap<>();
        response.put("id", id);
        return response;
    }

}
