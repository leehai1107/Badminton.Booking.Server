package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.entity.BookingOrders;

import java.util.List;

public interface BookingOrdersService {
    BookingOrdersResponseDTO createBookingOrder(BookingOrdersRequestDTO bookingOrdersRequestDTO);
    List<BookingOrdersResponseDTO> getAllBookingOrdersByUserId(Integer userId);
    BookingOrders updateStatus(Integer id);
    void updateStatusToFalse(Integer id);
}
