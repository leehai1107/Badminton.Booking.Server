package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.YardCheckinRequestDTO;
import com.main.badminton.booking.dto.response.YardCheckinResponseDTO;
import com.main.badminton.booking.entity.YardCheckins;
import java.util.List;
import java.util.Optional;

public interface YardCheckInService {
    List<YardCheckins> findAllByYardId(Integer id);
    YardCheckinResponseDTO checkIn(YardCheckinRequestDTO requestDTO);
    YardCheckinResponseDTO updateStatus(YardCheckinRequestDTO requestDTO);

    YardCheckins saveCheckIns(YardCheckins yardCheckins);
}
