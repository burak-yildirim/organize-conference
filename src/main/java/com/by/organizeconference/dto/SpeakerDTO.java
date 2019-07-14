package com.by.organizeconference.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author burakY
 */
public class SpeakerDTO {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("fullName")
    private String fullName;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
}
