package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.Payments;
import com.main.badminton.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Integer> {
    List<Payments> findByBookingOrders_User_Id(Integer userId);
    List<Payments> findByBookingOrdersYardsId(Integer yardId);
}
