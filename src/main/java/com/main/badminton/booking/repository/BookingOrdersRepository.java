package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.BookingOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingOrdersRepository extends JpaRepository<BookingOrders, Integer> {
}
