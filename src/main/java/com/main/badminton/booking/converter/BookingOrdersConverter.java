package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.entity.BookingOrders;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingOrdersConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public BookingOrdersConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookingOrders requestDtoToEntity(BookingOrdersRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, BookingOrders.class);
    }

    public BookingOrdersResponseDTO entityToResponseDto(BookingOrders bookingOrders) {
        return modelMapper.map(bookingOrders, BookingOrdersResponseDTO.class);
    }
}
