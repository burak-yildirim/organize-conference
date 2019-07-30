package com.by.organizeconference.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author burakY
 */
@Table(name="speaker_detail")
@Entity
public class SpeakerDetail {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    
    @Column(name="country")
    private String country;
    
    @Column(name="email")
    private String email;
    
    @Column(name="about")
    private String about;
    
    @JsonIgnore
    @OneToOne(mappedBy = "speakerDetail")
    private Speaker speaker;

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }
    

    @Override
    public String toString() {
        return "SpeakerDetail{" + "id=" + id + ", country=" + country + ", email=" + email + ", about=" + about + ", speakerId=" + (speaker == null ? null : speaker.getId()) + '}';
    }
    

}
