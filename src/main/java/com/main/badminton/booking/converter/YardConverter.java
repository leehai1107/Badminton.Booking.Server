package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.SlotResponseDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.entity.Slots;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.entity.Yards;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class YardConverter {

    @Autowired
    private ModelMapper modelMapper;

    public YardResponseDTO toResponseDTO(Yards yard) {
        YardResponseDTO yardResponseDTO = modelMapper.map(yard, YardResponseDTO.class);
        List<SlotResponseDTO> slotResponseDTOs = yard.getSlots().stream()
                .map(this::convertToSlotResponseDTO)
                .collect(Collectors.toList());
        yardResponseDTO.setSlots(slotResponseDTOs);
        return yardResponseDTO;
    }

    private SlotResponseDTO convertToSlotResponseDTO(Slots slot) {
        return modelMapper.map(slot, SlotResponseDTO.class);
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
        return modelMapper.map(yardRequestDTO, Yards.class);
    }

    public YardResponseDTO convertToDTO(Yards yards) {
        YardResponseDTO yardResponseDTO = modelMapper.map(yards, YardResponseDTO.class);
        yardResponseDTO.setHostId(yards.getHost().getId());
        List<SlotResponseDTO> slotResponseDTOs = yards.getSlots().stream()
                .map(this::convertToSlotResponseDTO)
                .collect(Collectors.toList());
        yardResponseDTO.setSlots(slotResponseDTOs);
        return yardResponseDTO;
    }
}
