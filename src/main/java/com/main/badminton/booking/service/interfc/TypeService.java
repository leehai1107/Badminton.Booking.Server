package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.TypeRequestDTO;
import com.main.badminton.booking.dto.response.TypeResponseDTO;

import java.util.List;

public interface TypeService {
    void createType(TypeRequestDTO typeRequestDTO);
    TypeResponseDTO updateType(Integer id, TypeRequestDTO typeRequestDTO);
    List<TypeResponseDTO> getAllTypes();
}
