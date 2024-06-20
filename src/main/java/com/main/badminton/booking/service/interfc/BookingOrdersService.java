package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;

public interface BookingOrdersService {
    BookingOrdersResponseDTO createBookingOrder(BookingOrdersRequestDTO bookingOrdersRequestDTO);
}
