package com.main.badminton.booking.config;

import com.main.badminton.booking.dto.TelephonesDTO;
import com.main.badminton.booking.entity.Telephones;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<TelephonesDTO, Telephones>() {

            @Override
            protected void configure() {
                map().getYards().setId(source.getYardId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Telephones, TelephonesDTO>() {
            @Override
            protected void configure() {
                map().setYardId(source.getYards().getId());
            }
        });
        return modelMapper;
    }
}