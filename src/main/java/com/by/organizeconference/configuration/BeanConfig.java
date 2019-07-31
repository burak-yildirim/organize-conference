package com.by.organizeconference.configuration;

import com.by.organizeconference.dto.ConferenceDTO;
import com.by.organizeconference.dto.DetailedSpeakerDTO;
import com.by.organizeconference.dto.SpeakerDTO;
import com.by.organizeconference.dto.TopicDTO;
import com.by.organizeconference.entity.Conference;
import com.by.organizeconference.entity.Speaker;
import com.by.organizeconference.entity.SpeakerDetail;
import com.by.organizeconference.entity.Topic;
import com.by.organizeconference.service.ConferenceService;
import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author burakY
 */
@Configuration
public class BeanConfig {
    
    @Autowired
    private ConferenceService cs;
    
    
    @Bean
    public Mapper<Topic, TopicDTO> topicEDMapper(Mapper<Speaker, SpeakerDTO> speakerEDMapper){
        return Mapping.from(Topic.class)
                .to(TopicDTO.class)
                .useMapper(speakerEDMapper)
                .replace(Topic::getConference, TopicDTO::getConferenceId)
                .withSkipWhenNull(Conference::getId)
                .mapper();
    }
    
    @Bean
    public Mapper<TopicDTO, Topic> topicDEMapper(Mapper<SpeakerDTO, Speaker> speakerDEMapper){
        return Mapping.from(TopicDTO.class)
                .to(Topic.class)
                .useMapper(speakerDEMapper)
                .replace(TopicDTO::getConferenceId, Topic::getConference)
                .withSkipWhenNull(cs::findById)
                .mapper();
    }
    
    @Bean
    public Mapper<Speaker, SpeakerDTO> speakerEDMapper(){
        return Mapping.from(Speaker.class)
                .to(SpeakerDTO.class)
                .omitInSource(Speaker::getSpeakerDetail)
                .omitInSource(Speaker::getTopics)
                .mapper();
    }
    
    @Bean
    public Mapper<SpeakerDTO, Speaker> speakerDEMapper(){
        return Mapping.from(SpeakerDTO.class)
                .to(Speaker.class)
                .omitInDestination(Speaker::getSpeakerDetail)
                .omitInDestination(Speaker::getTopics)
                .mapper();
    }
    
    @Bean
    public Mapper<Conference, ConferenceDTO> conferenceEDMapper( Mapper<Topic, TopicDTO> topicEDMapper){
        return Mapping.from(Conference.class)
                .to(ConferenceDTO.class)
                .useMapper(topicEDMapper)
                .omitInSource(Conference::getSpeakers)
                .mapper();
    }
    
    @Bean
    public Mapper<ConferenceDTO, Conference> conferenceDEMapper(Mapper<TopicDTO, Topic> topicDEMapper){
        return Mapping.from(ConferenceDTO.class)
                .to(Conference.class)
                .useMapper(topicDEMapper)
                .omitInDestination(Conference::getSpeakers)
                .mapper();
    }
    
    @Bean
    public Mapper<Speaker, DetailedSpeakerDTO> detailedSpeakerEDMapper(Mapper<Topic, TopicDTO> topicEDMapper){
        return Mapping.from(Speaker.class)
                .to(DetailedSpeakerDTO.class)
                .useMapper(topicEDMapper)
                .replace(Speaker::getSpeakerDetail, DetailedSpeakerDTO::getCountry)
                .withSkipWhenNull(SpeakerDetail::getCountry)
                .replace(Speaker::getSpeakerDetail, DetailedSpeakerDTO::getEmail)
                .withSkipWhenNull(SpeakerDetail::getEmail)
                .replace(Speaker::getSpeakerDetail, DetailedSpeakerDTO::getAbout)
                .withSkipWhenNull(SpeakerDetail::getAbout)
                .mapper();
    }
    
    @Bean
    public Mapper<DetailedSpeakerDTO, Speaker> detailedSpeakerDEMapper(Mapper<TopicDTO, Topic> topicDEMapper){
        return Mapping.from(DetailedSpeakerDTO.class)
                .to(Speaker.class)
                .useMapper(topicDEMapper)
                .omitInSource(DetailedSpeakerDTO::getCountry)
                .omitInSource(DetailedSpeakerDTO::getEmail)
                .omitInSource(DetailedSpeakerDTO::getAbout)
                .set(Speaker::getSpeakerDetail)
                .with(ds -> {
                    SpeakerDetail sd = new SpeakerDetail();
                    
                    sd.setCountry(ds.getCountry());
                    sd.setEmail(ds.getEmail());
                    sd.setAbout(ds.getAbout());
                    
                    return sd;
                })
                .mapper();
    }
    
}
