package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.entity.BookingOrders;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookingOrdersConverter {

    private final ModelMapper modelMapper;

    public BookingOrdersConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookingOrdersResponseDTO entityToResponseDto(BookingOrders bookingOrders) {
        return modelMapper.map(bookingOrders, BookingOrdersResponseDTO.class);
    }

    public BookingOrders requestDtoToEntity(BookingOrdersRequestDTO bookingOrdersRequestDTO) {
        return modelMapper.map(bookingOrdersRequestDTO, BookingOrders.class);
    }
}
