package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.PaymentRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.dto.response.SimplePaymentResponseDTO;
import com.main.badminton.booking.dto.response.SimpleYardResponseDTO;
import com.main.badminton.booking.dto.response.SlotResponseDTO;
import com.main.badminton.booking.entity.BookingOrders;
import com.main.badminton.booking.entity.Payments;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimplePaymentConverter {

    @Autowired
    private ModelMapper modelMapper;

    public SimplePaymentResponseDTO convertToDto(Payments payments) {
        SimplePaymentResponseDTO dto = modelMapper.map(payments, SimplePaymentResponseDTO.class);
        if (payments.getBookingOrders() != null) {
            BookingOrdersResponseDTO bookingOrdersDTO = convertBookingOrderToDto(payments.getBookingOrders());
            dto.setBookingOrder(bookingOrdersDTO);
        }
        return dto;
    }

    public Payments convertToEntity(PaymentRequestDTO paymentRequestDTO) {
        return modelMapper.map(paymentRequestDTO, Payments.class);
    }

    private BookingOrdersResponseDTO convertBookingOrderToDto(BookingOrders bookingOrders) {
        BookingOrdersResponseDTO dto = modelMapper.map(bookingOrders, BookingOrdersResponseDTO.class);

        if (bookingOrders.getYards() != null) {
            dto.setYard(modelMapper.map(bookingOrders.getYards(), SimpleYardResponseDTO.class));
        }

        if (bookingOrders.getUser() != null) {
            dto.setUserId(bookingOrders.getUser().getId());
        }

        if (bookingOrders.getSlots() != null) {
            dto.setSlot(modelMapper.map(bookingOrders.getSlots(), SlotResponseDTO.class));
        }

        return dto;
    }
}
