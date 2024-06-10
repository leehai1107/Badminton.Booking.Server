package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.Telephones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephonesRepository extends JpaRepository<Telephones, Integer> {
}
