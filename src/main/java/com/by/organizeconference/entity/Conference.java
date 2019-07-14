package com.by.organizeconference.entity;

import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author burakY
 */
@Table(name = "conference")
@Entity
public class Conference {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "start_time")
    private Date startTime;
    
    @Column(name = "end_time")
    private Date endTime;
    
    @ManyToMany
    @JoinTable(
        name = "conference_speaker",
        joinColumns = @JoinColumn(name = "conference_id"),
        inverseJoinColumns = @JoinColumn(name = "speaker_id") 
    )
    private List<Speaker> speakers;
    
    @OneToMany(mappedBy = "conference")
    private List<Topic> topics;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        List<String> speakerNames = speakers.stream().map(speaker -> speaker.getFullName()).collect(toList());
        List<String> topicTitles = topics.stream().map(topic -> topic.getTitle()).collect(toList());
        return "Conference{" + "id=" + id + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime + ", speakers=" + speakerNames + ", topics=" + topicTitles + '}';
    }
    
    
}
