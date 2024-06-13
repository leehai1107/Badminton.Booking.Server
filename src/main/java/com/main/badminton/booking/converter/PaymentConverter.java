package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.response.PaymentResponseDTO;
import com.main.badminton.booking.entity.Payments;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentResponseDTO convertToDto(Payments payments) {
        PaymentResponseDTO dto = modelMapper.map(payments, PaymentResponseDTO.class);
        if (payments.getBookingOrders() != null) {
            dto.setBookingOrderId(payments.getBookingOrders().getId());
        }
        return dto;
    }
}
