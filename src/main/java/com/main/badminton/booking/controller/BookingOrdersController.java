package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.service.interfc.BookingOrdersService;
import com.main.badminton.booking.utils.wapper.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bookingOrders")
public class BookingOrdersController {

    @Autowired
    private BookingOrdersService bookingOrdersService;

    @PostMapping("/create")
    public ResponseEntity<List<BookingOrdersResponseDTO>> createBookingOrders(@RequestBody List<BookingOrdersRequestDTO> bookingOrdersRequestDTOs) {
       try{
           List<BookingOrdersResponseDTO> createdBookingOrders = bookingOrdersRequestDTOs.stream()
                   .map(bookingOrdersService::createBookingOrder)
                   .collect(Collectors.toList());
           return new ResponseEntity<>(createdBookingOrders, HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @GetMapping("/user/{userId}")
    public List<BookingOrdersResponseDTO> getAllBookingOrdersByUserId(@PathVariable Integer userId) {
        return bookingOrdersService.getAllBookingOrdersByUserId(userId);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<String> updateBookingOrderStatus(@PathVariable Integer id) {
        try {
            bookingOrdersService.updateStatusToFalse(id);
            return ResponseEntity.ok("Booking order status updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
