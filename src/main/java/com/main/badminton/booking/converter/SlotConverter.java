package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.response.SlotResponseDTO;
import com.main.badminton.booking.entity.Slots;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlotConverter {
    @Autowired
    private ModelMapper modelMapper;

    public SlotResponseDTO toResponseDTO(Slots slot){
        return modelMapper.map(slot, SlotResponseDTO.class);
    }
}
