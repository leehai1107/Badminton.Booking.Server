package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.YardCheckins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YardCheckInRepository extends JpaRepository<YardCheckins, Integer> {

    @Query("SELECT yc FROM YardCheckins yc " +
            "JOIN yc.payments p " +
            "JOIN p.bookingOrders bo " +
            "JOIN bo.yards y " +
            "WHERE y.id = :id")
    List<YardCheckins> findAllByYardId(Integer id);
}
