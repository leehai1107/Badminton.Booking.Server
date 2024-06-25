package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.entity.Yards;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YardConverter {

    @Autowired
    private ModelMapper modelMapper;

    public YardResponseDTO toResponseDTO(Yards yard) {
        return modelMapper.map(yard, YardResponseDTO.class);
    }
    public Yards toEntity(YardRequestDTO dto, User host) {
        return Yards.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .provinceId(dto.getProvinceId())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .openTime(dto.getOpenTime())
                .closeTime(dto.getCloseTime())
                .host(host)
                .build();
    }
    public Yards toEntity(YardRequestDTO dto) {
        return Yards.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .provinceId(dto.getProvinceId())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .openTime(dto.getOpenTime())
                .closeTime(dto.getCloseTime())
                .build();
    }

    public void updateEntity(YardRequestDTO dto, Yards yard) {
        yard.setName(dto.getName());
        yard.setAddress(dto.getAddress());
        yard.setProvinceId(dto.getProvinceId());
        yard.setDescription(dto.getDescription());
        yard.setStatus(dto.getStatus());
        yard.setOpenTime(dto.getOpenTime());
        yard.setCloseTime(dto.getCloseTime());
    }

    public Yards convertToEntity(YardRequestDTO yardRequestDTO) {
        Yards yard = modelMapper.map(yardRequestDTO, Yards.class);
        return yard;
    }

    public YardResponseDTO convertToDTO(Yards yards) {
        YardResponseDTO yardResponseDTO = modelMapper.map(yards, YardResponseDTO.class);
        yardResponseDTO.setHostId(yards.getHost().getId());
        return yardResponseDTO;
    }

}