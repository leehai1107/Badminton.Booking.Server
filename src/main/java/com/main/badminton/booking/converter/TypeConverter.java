package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.TypeRequestDTO;
import com.main.badminton.booking.dto.response.TypeResponseDTO;
import com.main.badminton.booking.entity.Types;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeConverter {
    @Autowired
    private ModelMapper modelMapper;

    public Types ToEntity(TypeRequestDTO typeDTO){
        return modelMapper.map(typeDTO, Types.class);
    }

    public TypeResponseDTO ToResponseDTO(Types type){
        return modelMapper.map(type, TypeResponseDTO.class);
    }
}
