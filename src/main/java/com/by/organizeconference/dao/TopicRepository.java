/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.by.organizeconference.dao;

import com.by.organizeconference.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author supercoder
 */
public interface TopicRepository extends JpaRepository<Topic, Long>{
    
}
