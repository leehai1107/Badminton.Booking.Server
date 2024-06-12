package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.Yards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface YardRepository extends JpaRepository<Yards, Integer> {
    List<Yards> findByOpenTimeBeforeAndCloseTimeAfter(LocalDateTime open, LocalDateTime close);
}
