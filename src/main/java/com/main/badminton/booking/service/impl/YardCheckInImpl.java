package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.YardCheckinConverter;
import com.main.badminton.booking.dto.request.YardCheckinRequestDTO;
import com.main.badminton.booking.dto.response.YardCheckinResponseDTO;
import com.main.badminton.booking.entity.YardCheckins;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.repository.YardCheckInRepository;
import com.main.badminton.booking.service.interfc.YardCheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class YardCheckInImpl implements YardCheckInService {
    @Autowired
    private YardCheckInRepository yardCheckInRepository;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private YardCheckinConverter yardCheckinConverter;

    @Override
    public List<YardCheckins> findAllByYardId(Integer id) {
        List<YardCheckins> list = yardCheckInRepository.findAllByYardId(id);
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
        YardCheckins yardCheckins = yardCheckInRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new RuntimeException("Check-in not found"));

        yardCheckins.setStatus(requestDTO.getStatus());

        if (requestDTO.getCheckInTime() != null) {
            yardCheckins.setCheckInTime(requestDTO.getCheckInTime());
        }
        if (requestDTO.getCheckOutTime() != null) {
            yardCheckins.setCheckOutTime(requestDTO.getCheckOutTime());
        }
        yardCheckins.setCheckInBy(userRepo.findById(requestDTO.getCheckInById()).orElse(null));
        YardCheckins updatedYardCheckins = yardCheckInRepository.save(yardCheckins);
        return yardCheckinConverter.toDTO(updatedYardCheckins);
    }

    @Override
    public YardCheckins saveCheckIns(YardCheckins yardCheckins) {
       return yardCheckInRepository.save(yardCheckins);
    }
    @Override
    public YardCheckins findByPaymentId(Integer paymentId) {
        return yardCheckInRepository.findByPaymentId(paymentId);
    }
}
