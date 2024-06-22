package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.service.interfc.BookingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookingOrders")
public class BookingOrdersController {

    @Autowired
    private BookingOrdersService bookingOrdersService;

    @PostMapping
    public ResponseEntity<BookingOrdersResponseDTO> createBookingOrder(@RequestBody BookingOrdersRequestDTO bookingOrdersRequestDTO) {
        BookingOrdersResponseDTO createdBookingOrder = bookingOrdersService.createBookingOrder(bookingOrdersRequestDTO);
        return new ResponseEntity<>(createdBookingOrder, HttpStatus.CREATED);
    }
}
