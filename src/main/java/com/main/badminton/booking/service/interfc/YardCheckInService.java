package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.YardCheckinRequestDTO;
import com.main.badminton.booking.dto.response.YardCheckinResponseDTO;
import com.main.badminton.booking.entity.YardCheckins;
import java.util.List;
public interface YardCheckInService {
    public List<YardCheckins> findAllYardCheckIns();
    YardCheckinResponseDTO checkIn(YardCheckinRequestDTO requestDTO);
    YardCheckinResponseDTO updateStatus(YardCheckinRequestDTO requestDTO);
}
