package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.Types;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Types, Integer> {
}