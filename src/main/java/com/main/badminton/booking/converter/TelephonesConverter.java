package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.TelephonesDTO;
import com.main.badminton.booking.entity.Telephones;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelephonesConverter {
    @Autowired
    private ModelMapper modelMapper;

    public TelephonesDTO toDTO(Telephones telephones) {
        return modelMapper.map(telephones, TelephonesDTO.class);
    }

    public Telephones toEntity(TelephonesDTO telephonesDTO) {
        return modelMapper.map(telephonesDTO, Telephones.class);
    }
}
