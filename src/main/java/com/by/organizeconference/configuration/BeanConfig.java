package com.by.organizeconference.configuration;

import com.by.organizeconference.dto.ConferenceDTO;
import com.by.organizeconference.dto.SpeakerDTO;
import com.by.organizeconference.dto.TopicDTO;
import com.by.organizeconference.entity.Conference;
import com.by.organizeconference.entity.Speaker;
import com.by.organizeconference.entity.Topic;
import com.by.organizeconference.service.ConferenceService;
import com.remondis.remap.Mapper;
import com.remondis.remap.Mapping;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
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
    public ModelMapper getModelMapper(){
        ModelMapper mapper = new ModelMapper();
        
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        
        Converter<TopicDTO, Topic> topicConverter = context -> {
            System.out.println("********CUSTOM TOPIC CONVERTER IS USED*********");
            
            TopicDTO topicDTO = context.getSource();
            Topic topic = context.getDestination();
            topic.setId(topicDTO.getId());
            topic.setTitle(topicDTO.getTitle());
            topic.setStartTime(topicDTO.getStartTime());
            topic.setEndTime(topicDTO.getEndTime());
            topic.setConference(cs.findById(topicDTO.getConferenceId()));
            
            return topic;
        };
        
        mapper.addConverter(topicConverter);
        
//        PropertyMap<TopicDTO, Topic> topicDTOHourMap = new PropertyMap<TopicDTO, Topic>() {
//            @Override
//            protected void configure(){
//                map().setStartTime(source.getStartTime());
//                map().setEndTime(source.getEndTime());
//            }
//        };
//        mapper.addMappings(topicDTOHourMap);
        
        return mapper;
    }
    
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
    public Mapper<Speaker, SpeakerDTO> speakerEDMapper(){
        return Mapping.from(Speaker.class)
                .to(SpeakerDTO.class)
                .omitInSource(Speaker::getSpeakerDetail)
                .omitInSource(Speaker::getTopics)
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
                .omitInSource(Conference::getSpeakers)
                .useMapper(topicEDMapper)
                .mapper();
    }
    
    @Bean
    public Mapper<ConferenceDTO, Conference> conferenceDEMapper(Mapper<TopicDTO, Topic> topicDEMapper){
        return Mapping.from(ConferenceDTO.class)
                .to(Conference.class)
                .omitInDestination(Conference::getSpeakers)
                .useMapper(topicDEMapper)
                .mapper();
    }
    
}
