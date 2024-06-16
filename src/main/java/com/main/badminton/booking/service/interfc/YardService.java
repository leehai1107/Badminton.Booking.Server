package com.main.badminton.booking.service.interfc;

import java.util.List;

import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;

public interface YardService {
    void createYard(YardRequestDTO requestDTO);
    List<Integer> getProvinceIds();
    YardResponseDTO updateYard(Integer id, YardRequestDTO yardRequestDTO);
    YardResponseDTO getYardById(Integer id);
    List<YardResponseDTO> getAllYards(int pageNumber);
    List<YardResponseDTO> getAllYardsByActiveStatus(int pageNumber);
    List<YardResponseDTO> getYardsByName(String name, int pageNumber);
}
