package com.by.organizeconference.service;

import com.by.organizeconference.entity.SpeakerDetail;
import java.util.List;
import java.util.Map;

/**
 *
 * @author burakY
 */
public interface SpeakerDetailService {
    
    public List<SpeakerDetail> findAll();
    
    public SpeakerDetail findById(Long id);
    
    public SpeakerDetail save(SpeakerDetail speakerDetail);
    
    public Map<String, Long> deleteById(Long id);
    
}
