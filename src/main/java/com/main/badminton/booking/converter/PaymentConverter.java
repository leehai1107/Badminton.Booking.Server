package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.PaymentRequestDTO;
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
            dto.setYardName(payments.getBookingOrders().getYards().getName());
            dto.setBookingAt(payments.getBookingOrders().getBookingAt());
            if (payments.getBookingOrders().getSlots() != null) {
                dto.setSlotStartTime(payments.getBookingOrders().getSlots().getStartTime());
                dto.setSlotEndTime(payments.getBookingOrders().getSlots().getEndTime());
            }
        }
        return dto;
    }

    public Payments convertToEntity(PaymentRequestDTO paymentRequestDTO) {
        return modelMapper.map(paymentRequestDTO, Payments.class);
    }
}
