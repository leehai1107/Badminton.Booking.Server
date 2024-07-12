package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.TelephonesDTO;
import com.main.badminton.booking.entity.Telephones;
import com.main.badminton.booking.repository.YardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelephonesConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private YardRepository yardRepository;

    public TelephonesDTO toDTO(Telephones telephones) {
        return modelMapper.map(telephones, TelephonesDTO.class);
    }

    public Telephones toEntity(TelephonesDTO telephonesDTO) {
        Telephones telephones = modelMapper.map(telephonesDTO, Telephones.class);
        if(telephonesDTO.getYardId() != null) {
            telephones.setYards(yardRepository.findById(telephonesDTO.getYardId()).orElse(null));
        }
        return telephones;
    }
}
