package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.service.interfc.BookingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bookingOrders")
public class BookingOrdersController {

    @Autowired
    private BookingOrdersService bookingOrdersService;

    @PostMapping("/bulk")
    public ResponseEntity<List<BookingOrdersResponseDTO>> createBookingOrders(@RequestBody List<BookingOrdersRequestDTO> bookingOrdersRequestDTOList) {
        List<BookingOrdersResponseDTO> createdBookingOrders = new ArrayList<>();
        for (BookingOrdersRequestDTO requestDTO : bookingOrdersRequestDTOList) {
            BookingOrdersResponseDTO createdBookingOrder = bookingOrdersService.createBookingOrder(requestDTO);
            createdBookingOrders.add(createdBookingOrder);
        }
        return new ResponseEntity<>(createdBookingOrders, HttpStatus.CREATED);
    }
}
