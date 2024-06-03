package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.entity.Yards;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YardConverter {

    @Autowired
    private ModelMapper modelMapper;

    public YardResponseDTO toYardResponseDTO(Yards yard) {
        return modelMapper.map(yard, YardResponseDTO.class);
    }
}