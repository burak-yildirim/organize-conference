package com.by.organizeconference.dao;

import com.by.organizeconference.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author supercoder
 */
public interface ConferenceRepository extends JpaRepository<Conference, Long> {
    
}
