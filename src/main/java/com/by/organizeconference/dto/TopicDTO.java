package com.by.organizeconference.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author burakY
 */
public class TopicDTO {
    
    @JsonProperty
    private Long id;
    
    @JsonProperty
    private String title;
    
    @JsonProperty
    private SpeakerDTO speaker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SpeakerDTO getSpeaker() {
        return speaker;
    }

    public void setSpeaker(SpeakerDTO speaker) {
        this.speaker = speaker;
    }
    
}
