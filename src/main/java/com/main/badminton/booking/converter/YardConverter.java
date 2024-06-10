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

    public YardResponseDTO toResponseDTO(Yards yard) {
        YardResponseDTO dto = new YardResponseDTO();
        dto.setId(yard.getId());
        dto.setName(yard.getName());
        dto.setAddress(yard.getAddress());
        dto.setProvinceId(yard.getProvinceId());
        dto.setDescription(yard.getDescription());
        dto.setStatus(yard.getStatus());
        dto.setOpenTime(yard.getOpenTime());
        dto.setCloseTime(yard.getCloseTime());
        dto.setCreateDate(yard.getCreateDate());
        dto.setUpdateDate(yard.getUpdateDate());
        dto.setCreateBy(yard.getCreateBy());
        dto.setUpdateBy(yard.getUpdateBy());
        return dto;
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

    public void updateEntity(YardRequestDTO dto, Yards yard) {
        yard.setName(dto.getName());
        yard.setAddress(dto.getAddress());
        yard.setProvinceId(dto.getProvinceId());
        yard.setDescription(dto.getDescription());
        yard.setStatus(dto.getStatus());
        yard.setOpenTime(dto.getOpenTime());
        yard.setCloseTime(dto.getCloseTime());
    }

}
