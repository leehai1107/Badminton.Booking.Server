package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.BookingOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingOrdersRepository extends JpaRepository<BookingOrders, Integer> {
    List<BookingOrders> findByUserId(Integer userId);

    @Query("SELECT COUNT(*) FROM BookingOrders bo WHERE bo.yards.id = :yardId AND bo.slots.id = :slotId AND bo.tournamentEnd >= :tournamentStart")
    long countByYardsIdAndSlotsIdAndBookingAt(Integer yardId, Integer slotId, LocalDate tournamentStart);

    long countByYardsIdAndSlotsIdAndBookingAtAndUserId(Integer yardId, Integer slotId, LocalDateTime bookingTime, Integer userId);
}
