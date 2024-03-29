package com.by.organizeconference.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    
    @Column(name = "start_date")
    private Date startDate;
    
    @Column(name = "end_date")
    private Date endDate;
    
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        Set<Speaker> ss = new HashSet<>();
        
        if(this.speakers != null)
            ss.addAll(this.speakers);
        topics.forEach(t -> ss.add(t.getSpeaker()));
        
        this.speakers = ss.stream().collect(toList());
        
        this.topics = topics;
    }

    @Override
    public String toString() {
        List<String> speakerNames = speakers != null ? speakers.stream().map(speaker -> speaker.getFullName()).collect(toList()) : null;
        List<String> topicTitles = topics!= null ? topics.stream().map(topic -> topic.getTitle()).collect(toList()) : null;
        return "Conference{" + "id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", speakers=" + speakerNames + ", topics=" + topicTitles + '}';
    }
    
    
}
