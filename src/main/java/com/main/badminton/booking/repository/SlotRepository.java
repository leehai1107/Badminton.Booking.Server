package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.Slots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slots, Long> {
}
