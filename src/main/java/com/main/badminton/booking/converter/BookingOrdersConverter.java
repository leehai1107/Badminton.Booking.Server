package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingOrdersConverter {

    @Autowired
    private final ModelMapper modelMapper;

    public BookingOrdersConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

// Convert BookingOrdersRequestDTO to BookingOrders
public BookingOrders requestDtoToEntity(BookingOrdersRequestDTO bookingOrdersRequestDTO) {
    BookingOrders bookingOrders = modelMapper.map(bookingOrdersRequestDTO, BookingOrders.class);

    // Manually set the references to the other entities
    Yards yards = new Yards();
    yards.setId(bookingOrdersRequestDTO.getYardId());
    bookingOrders.setYards(yards);

    User user = new User();
    user.setId(bookingOrdersRequestDTO.getUserId());
    bookingOrders.setUser(user);

    Schedules schedules = new Schedules();
    schedules.setId(bookingOrdersRequestDTO.getScheduleId());
    bookingOrders.setSchedules(schedules);

    Slots slots = new Slots();
    slots.setId(bookingOrdersRequestDTO.getSlotId());
    bookingOrders.setSlots(slots);

    return bookingOrders;
}


    // Convert BookingOrders to BookingOrdersResponseDTO
    public BookingOrdersResponseDTO entityToResponseDto(BookingOrders bookingOrders) {
        BookingOrdersResponseDTO dto = new BookingOrdersResponseDTO();

        dto.setYardId(bookingOrders.getYards().getId());
        dto.setUserId(bookingOrders.getUser().getId());
        dto.setScheduleId(bookingOrders.getSchedules().getId());
        dto.setSlotId(bookingOrders.getSlots().getId());
        dto.setStatus(bookingOrders.getStatus());

        return dto;
    }
}
