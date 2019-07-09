package com.by.organizeconference.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author burakY
 */
@Entity
@Table(name="speaker_detail")
public class SpeakerDetail {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    private Long id;
    
    @Column(name="country")
    private String country;
    
    @Column(name="email")
    private String email;
    
    @Column(name="about")
    private String about;

    
    
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

    @Override
    public String toString() {
        return "SpeakerDetail{" + "id=" + id + ", country=" + country + ", email=" + email + ", about=" + about + '}';
    }
    
    
}
