package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.BookingOrdersConverter;

import com.main.badminton.booking.dto.request.BookingOrdersRequestDTO;
import com.main.badminton.booking.dto.response.BookingOrdersResponseDTO;
import com.main.badminton.booking.entity.BookingOrders;
import com.main.badminton.booking.repository.BookingOrdersRepository;
import com.main.badminton.booking.service.interfc.BookingOrdersService;
import com.main.badminton.booking.utils.logger.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingOrdersServiceImpl implements BookingOrdersService {

    @Autowired
    private BookingOrdersRepository bookingOrdersRepository;

    @Autowired
    private BookingOrdersConverter bookingOrdersConverter;

    @Transactional
    @Override
    public BookingOrdersResponseDTO createBookingOrder(BookingOrdersRequestDTO bookingOrdersRequestDTO) {
        // Convert DTO to entity
        BookingOrders bookingOrders = bookingOrdersConverter.requestDtoToEntity(bookingOrdersRequestDTO);

        // Check if the slot is available for booking
        if (!isSlotAvailable(bookingOrders.getYards().getId(), bookingOrders.getSlots().getId(), bookingOrders.getTournamentStart())) {
            throw new RuntimeException("Slot is already booked for the selected time.");
        }

        // Check if another user has already booked the same slot
//        if (!isSlotAvailableForUser(bookingOrders.getYards().getId(), bookingOrders.getSlots().getId(), bookingOrders.getBookingAt(), bookingOrders.getUser().getId())) {
//            throw new RuntimeException("Another user has already booked the slot.");
//        }

        // Set the current date and save the booking
        bookingOrders.setBookingAt(LocalDateTime.now());
        BookingOrders savedBookingOrders = bookingOrdersRepository.save(bookingOrders);

        // Convert entity to response DTO and return
        return bookingOrdersConverter.entityToResponseDto(savedBookingOrders);
    }

    private boolean isSlotAvailable(Integer yardId, Integer slotId, LocalDate tournamentStart) {
        // Check if there's any booking for the same yard, slot, and time
        return bookingOrdersRepository.countByYardsIdAndSlotsIdAndBookingAt(yardId, slotId, tournamentStart) == 0;
    }

    private boolean isSlotAvailableForUser(Integer yardId, Integer slotId, LocalDateTime bookingTime, Integer userId) {
        // Check if another user has already booked the same yard, slot, and time
        return bookingOrdersRepository.countByYardsIdAndSlotsIdAndBookingAtAndUserId(yardId, slotId, bookingTime, userId) == 0;
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

    @Override
// Every 10 seconds
    @Scheduled(cron = "*/10 * * * * *")
    public void CornJobUpdateOrder() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.minusMinutes(5);
        List<BookingOrders> exprireList = bookingOrdersRepository.findExpiredBooking(expirationTime);

        for (BookingOrders bookingOrders : exprireList) {
            bookingOrders.setStatus(false);
            bookingOrdersRepository.save(bookingOrders);
        }
    }
}
