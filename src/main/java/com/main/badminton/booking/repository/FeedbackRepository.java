package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.FeedBacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBacks, Integer> {
    @Query("SELECT f FROM FeedBacks f " +
            "JOIN f.payments p " +
            "JOIN p.bookingOrders b " +
            "JOIN b.yards y " +
            "WHERE y.id = :yardId")
    List<FeedBacks> findByYardId(@Param("yardId") int yardId);
}
