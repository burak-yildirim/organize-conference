package com.by.organizeconference.service;

import com.by.organizeconference.dao.SpeakerRepository;
import com.by.organizeconference.entity.Speaker;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author burakY
 */
@Service
public class SpeakerServiceImpl implements SpeakerService {
    
    @Autowired
    private SpeakerRepository speakerRepository;

    @Override
    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    @Override
    public Speaker findById(Long id) {
        return speakerRepository.findById(id).get();
    }

    @Override
    public void save(Speaker speaker) {
        speakerRepository.save(speaker);
    }

    @Override
    public void deleteById(Long id) {
        speakerRepository.deleteById(id);
    }
    
}
