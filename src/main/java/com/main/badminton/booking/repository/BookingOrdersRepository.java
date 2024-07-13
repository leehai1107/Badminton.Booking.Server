package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.BookingOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingOrdersRepository extends JpaRepository<BookingOrders, Integer> {
    List<BookingOrders> findByUserId(Integer userId);

    @Query("SELECT COUNT(bo) FROM booking_orders bo WHERE bo.yards.id = :yardId AND bo.slots.id = :slotId AND bo.bookingAt = :bookingTime")
    long countByYardsIdAndSlotsIdAndBookingAt(@Param("yardId") Integer yardId, @Param("slotId") Integer slotId, @Param("bookingTime") LocalDateTime bookingTime);

    long countByYardsIdAndSlotsIdAndBookingAtAndUserId(Integer yardId, Integer slotId, LocalDateTime bookingTime, Integer userId);
}
