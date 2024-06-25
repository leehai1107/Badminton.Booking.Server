package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.service.interfc.BookingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookingOrders")
public class BookingOrdersController {

    @Autowired
    private BookingOrdersService bookingOrdersService;

    @PostMapping
    public ResponseEntity<List<BookingOrdersResponseDTO>> createBookingOrders(@RequestBody List<BookingOrdersRequestDTO> bookingOrdersRequestDTOs) {
        List<BookingOrdersResponseDTO> createdBookingOrders = bookingOrdersRequestDTOs.stream()
                .map(bookingOrdersService::createBookingOrder)
                .collect(Collectors.toList());
        return new ResponseEntity<>(createdBookingOrders, HttpStatus.CREATED);
    }
}
