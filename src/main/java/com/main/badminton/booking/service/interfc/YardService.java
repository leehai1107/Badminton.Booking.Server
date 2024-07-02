package com.main.badminton.booking.service.interfc;

import java.util.List;

import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.entity.Yards;

public interface YardService {
    YardResponseDTO createYard(YardRequestDTO yardRequestDTO);
    List<Integer> getProvinceIds();
    YardResponseDTO updateYard(Integer id, YardRequestDTO yardRequestDTO);
    YardResponseDTO getYardById(Integer id);
    List<YardResponseDTO> getAllYards(int pageNumber);
    List<YardResponseDTO> getAllYardsByActiveStatus(int pageNumber);
    List<YardResponseDTO> getYardsByName(String name, int pageNumber);
    List<YardResponseDTO> getAllYardsByHostId(Integer hostId);

    List<YardResponseDTO> getRandomYard();
}
