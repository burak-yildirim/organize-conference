package com.by.organizeconference.dao;

import com.by.organizeconference.entity.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author supercoder
 */
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
    
}
