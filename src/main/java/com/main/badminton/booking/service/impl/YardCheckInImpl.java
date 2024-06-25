package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.YardCheckinConverter;
import com.main.badminton.booking.dto.request.YardCheckinRequestDTO;
import com.main.badminton.booking.dto.response.YardCheckinResponseDTO;
import com.main.badminton.booking.entity.YardCheckins;
import com.main.badminton.booking.repository.YardCheckInRepository;
import com.main.badminton.booking.service.interfc.YardCheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class YardCheckInImpl implements YardCheckInService {
    @Autowired
    private YardCheckInRepository yardCheckInRepository;
    @Autowired
    private YardCheckinConverter yardCheckinConverter;

    @Override
    public List<YardCheckins> findAllYardCheckIns() {
        List<YardCheckins> list = yardCheckInRepository.findAll();
        if(list != null){
            return list;
        }
        return null;
    }

    @Override
    public YardCheckinResponseDTO checkIn(YardCheckinRequestDTO requestDTO) {
        YardCheckins yardCheckins = yardCheckinConverter.toEntity(requestDTO);
        YardCheckins savedYardCheckins = yardCheckInRepository.save(yardCheckins);
        return yardCheckinConverter.toDTO(savedYardCheckins);
    }

    @Override
    public YardCheckinResponseDTO updateStatus(YardCheckinRequestDTO requestDTO) {
        YardCheckins yardCheckins = yardCheckInRepository.findById(Long.valueOf(requestDTO.getId()))
                .orElseThrow(() -> new RuntimeException("Check-in not found"));

        yardCheckins.setStatus(requestDTO.getStatus());
        YardCheckins updatedYardCheckins = yardCheckInRepository.save(yardCheckins);
        return yardCheckinConverter.toDTO(updatedYardCheckins);
    }
}
