package com.by.organizeconference.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 'startTime' and 'endTime' is at 'hh:mm' format
 * @author burakY
 */
public class TopicDTO {
    
    @JsonProperty
    private Long id;
    
    @JsonProperty
    private String title;
    
    @JsonProperty
    private SpeakerDTO speaker;
    
    @JsonProperty
    private String startTime;
    
    @JsonProperty
    private String endTime;
    
    @JsonProperty
    private Long conferenceId;
    
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
    
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Override
    public String toString() {
        return "TopicDTO@" + hashCode() +"{" + "id=" + id + ", title=" + title + ", speaker=" + speaker + ", startTime=\"" + startTime + "\", endTime=\"" + endTime + "\", conferenceId=" + conferenceId + '}';
    }
    
}
