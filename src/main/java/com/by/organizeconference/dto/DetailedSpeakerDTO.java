package com.by.organizeconference.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author burakY
 */
public class DetailedSpeakerDTO {
    
    private Long id;
    
    private String fullName;
    
    private List<TopicDTO> topics;
    
    private String country;
    
    private String email;
    
    private String about;

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

    public List<TopicDTO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
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
        return "DetailedSpeakerDTO{" + "id=" + id + ", fullName=" + fullName + ", topics=" + topics + ", country=" + country + ", email=" + email + ", about=" + about + '}';
    }
    
}
