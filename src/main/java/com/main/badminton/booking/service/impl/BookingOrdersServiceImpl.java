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

import java.time.LocalDate;

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
        bookingOrders.setBookingAt(LocalDate.now());  // Set the current date
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
}
