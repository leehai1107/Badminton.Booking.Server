package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.response.YardImageDTO;
import com.main.badminton.booking.entity.YardImages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YardImagesConverter {
    @Autowired
    private ModelMapper modelMapper;

    public YardImageDTO toDTO(YardImages yardImages){
        return modelMapper.map(yardImages, YardImageDTO.class);
    }

    public YardImages toEntity(YardImageDTO yardImageDTO){
        return modelMapper.map(yardImageDTO, YardImages.class);
    }
}
