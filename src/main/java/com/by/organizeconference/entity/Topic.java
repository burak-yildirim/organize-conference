package com.by.organizeconference.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 'startTime' and 'endTime' is at 'hh:mm' format
 * @author burakY
 */
@Table(name = "topic")
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;
    
    @Column(name = "start_time")
    private String startTime;
    
    @Column(name = "end_time")
    private String endTime;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
    
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

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = hourMinuteFormatter(startTime);
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = hourMinuteFormatter(endTime);
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
    
    @Override
    public String toString() {
        return "Topic{" + "id=" + id + ", title=" + title + ", speaker=" + speaker + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }
    
    private String hourMinuteFormatter(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            return formatter.format(formatter.parse(time));
        } catch (Exception e) { e.printStackTrace(); }
        
        return null;
    }
}
