package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.BookingOrdersConverter;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.entity.BookingOrders;
import com.main.badminton.booking.repository.BookingOrdersRepository;
import com.main.badminton.booking.service.interfc.BookingOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingOrdersServiceImpl implements BookingOrdersService {

    @Autowired
    private BookingOrdersRepository bookingOrdersRepository;

    @Autowired
    private BookingOrdersConverter bookingOrdersConverter;

    @Override
    @Transactional
    public BookingOrdersResponseDTO createBookingOrder(BookingOrdersRequestDTO bookingOrdersRequestDTO) {
        BookingOrders bookingOrders = bookingOrdersConverter.requestDtoToEntity(bookingOrdersRequestDTO);
        bookingOrders.setBookingAt(LocalDateTime.now());  // Set the current date
        BookingOrders savedBookingOrders = bookingOrdersRepository.save(bookingOrders);
        return bookingOrdersConverter.entityToResponseDto(savedBookingOrders);
    }

    @Override
    public BookingOrders updateStatus(Integer id) {
        BookingOrders bookingOrders = bookingOrdersRepository.findById(id).get();
        bookingOrders.setStatus(true);
        bookingOrdersRepository.save(bookingOrders);
        return bookingOrders;
    }
    @Override
    public List<BookingOrdersResponseDTO> getAllBookingOrdersByUserId(Integer userId) {
        List<BookingOrders> bookingOrdersList = bookingOrdersRepository.findByUserId(userId);
        return bookingOrdersList.stream()
                .map(bookingOrdersConverter::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateStatusToFalse(Integer id) {
        BookingOrders bookingOrders = bookingOrdersRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Booking order not found with id " + id));
        bookingOrders.setStatus(false);  // Set status to false
        bookingOrdersRepository.save(bookingOrders);
    }
}
