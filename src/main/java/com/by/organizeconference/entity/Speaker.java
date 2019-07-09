package com.by.organizeconference.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author burakY
 */
@Entity
@Table(name="speaker")
public class Speaker {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="full_name")
    private String fullName;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="speaker_detail_id")
    private SpeakerDetail speakerDetail;
    
    // @ManyToMany to Topic

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

    public SpeakerDetail getSpeakerDetail() {
        return speakerDetail;
    }

    public void setSpeakerDetail(SpeakerDetail speakerDetail) {
        this.speakerDetail = speakerDetail;
    }

    @Override
    public String toString() {
        return "Speaker{" + "id=" + id + ", fullName=" + fullName + ", speakerDetail=" + speakerDetail + '}';
    }
    
    
}
