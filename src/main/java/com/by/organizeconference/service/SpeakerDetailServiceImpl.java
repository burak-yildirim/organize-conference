package com.by.organizeconference.service;

import com.by.organizeconference.dao.SpeakerDetailRepository;
import com.by.organizeconference.entity.SpeakerDetail;
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
public class SpeakerDetailServiceImpl implements SpeakerDetailService {
    
    @Autowired
    private SpeakerDetailRepository detailRepo;

    @Override
    public List<SpeakerDetail> findAll() {
        return detailRepo.findAll();
    }

    @Override
    public SpeakerDetail findById(Long id) {
        return detailRepo.findById(id).orElseGet(SpeakerDetail::new);
    }

    @Override
    public SpeakerDetail save(SpeakerDetail speakerDetail) {
        return detailRepo.save(speakerDetail);
    }

    @Override
    public Map<String, Long> deleteById(Long id) {
        detailRepo.deleteById(id);
        
        Map<String, Long> response = new HashMap<>();
        response.put("id", id);
        return response;
    }

}
