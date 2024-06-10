package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;

import java.util.List;

public interface YardService {
    YardResponseDTO createYard(YardRequestDTO request);
    List<Integer> getProvinceIds();
    YardResponseDTO getYardById(Integer id);
}
