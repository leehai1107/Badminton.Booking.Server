package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.YardCheckins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YardCheckInRepository extends JpaRepository<YardCheckins, Long> {
}
