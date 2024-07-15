package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.YardCheckinRequestDTO;
import com.main.badminton.booking.dto.response.YardCheckinResponseDTO;
import com.main.badminton.booking.entity.Payments;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.entity.YardCheckins;
import com.main.badminton.booking.repository.PaymentRepository;
import com.main.badminton.booking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class YardCheckinConverter {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepo userRepository;

    public YardCheckins toEntity(YardCheckinRequestDTO dto) {
        Payments payment = paymentRepository.findById(dto.getPaymentId()).orElse(null);
        User user = userRepository.findById(dto.getUserId()).orElse(null);
        User checkInBy = userRepository.findById(dto.getCheckInById()).orElse(null);

        return YardCheckins.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .checkInTime(dto.getCheckInTime())
                .checkOutTime(dto.getCheckOutTime())
                .payments(payment)
                .checkInBy(checkInBy)
                .build();
    }

    public YardCheckinResponseDTO toDTO(YardCheckins entity) {
        return YardCheckinResponseDTO
                .builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .checkInTime(entity.getCheckInTime())
                .checkOutTime(entity.getCheckOutTime())
                .paymentId(entity.getPayments().getId())
                .checkInById(entity.getCheckInBy().getId())
                .build();
    }
}
