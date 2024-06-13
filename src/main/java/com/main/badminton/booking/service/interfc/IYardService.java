package com.main.badminton.booking.service.interfc;

import java.util.List;

import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.entity.Yards;

public interface IYardService {
    List<YardResponseDTO> getAllYards(int pageNumber);
    List<YardResponseDTO> getAllYardsByActiveStatus(int pageNumber);
    List<YardResponseDTO> getYardsByName(String name, int pageNumber);
}
