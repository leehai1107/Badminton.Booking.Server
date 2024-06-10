package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.entity.User;

import java.util.List;

public interface YardService {
    void createYard(YardRequestDTO requestDTO, User host);
    List<Integer> getProvinceIds();
    YardResponseDTO updateYard(Integer id, YardRequestDTO yardRequestDTO);
    YardResponseDTO getYardById(Integer id);
}
