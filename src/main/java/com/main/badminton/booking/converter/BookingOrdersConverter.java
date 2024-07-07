package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.dto.response.SimpleYardResponseDTO;
import com.main.badminton.booking.dto.response.SlotResponseDTO;
import com.main.badminton.booking.entity.BookingOrders;
import com.main.badminton.booking.entity.Slots;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.entity.Yards;
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

    public BookingOrders requestDtoToEntity(BookingOrdersRequestDTO bookingOrdersRequestDTO) {
        BookingOrders bookingOrders = modelMapper.map(bookingOrdersRequestDTO, BookingOrders.class);

        Yards yards = new Yards();
        yards.setId(bookingOrdersRequestDTO.getYardId());
        bookingOrders.setYards(yards);

        User user = new User();
        user.setId(bookingOrdersRequestDTO.getUserId());
        bookingOrders.setUser(user);

        Slots slots = new Slots();
        slots.setId(bookingOrdersRequestDTO.getSlotId());
        bookingOrders.setSlots(slots);

        bookingOrders.setTournamentStart(bookingOrdersRequestDTO.getTournamentStart());
        bookingOrders.setTournamentEnd(bookingOrdersRequestDTO.getTournamentEnd());

        return bookingOrders;
    }

    public BookingOrdersResponseDTO entityToResponseDto(BookingOrders bookingOrders) {
        BookingOrdersResponseDTO dto = modelMapper.map(bookingOrders, BookingOrdersResponseDTO.class);

        dto.setId(bookingOrders.getId());
        dto.setBookingAt(bookingOrders.getBookingAt());
        dto.setStatus(bookingOrders.getStatus());
        dto.setYard(convertToSimpleYardResponseDTO(bookingOrders.getYards()));
        dto.setUserId(bookingOrders.getUser().getId());
        dto.setSlot(convertToSlotResponseDTO(bookingOrders.getSlots()));
        dto.setTournamentStart(bookingOrders.getTournamentStart());
        dto.setTournamentEnd(bookingOrders.getTournamentEnd());

        return dto;
    }

    private SimpleYardResponseDTO convertToSimpleYardResponseDTO(Yards yards) {
        return modelMapper.map(yards, SimpleYardResponseDTO.class);
    }

    private SlotResponseDTO convertToSlotResponseDTO(Slots slots) {
        return modelMapper.map(slots, SlotResponseDTO.class);
    }
}
