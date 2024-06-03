package com.main.badminton.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.main.badminton.booking.entity.Yards;

@Repository
public interface YardRepository extends JpaRepository<Yards, Integer>{
    @Query("SELECT u FROM Yards u where u.status = true")
    List<Yards> findAllByActiveStatus();
}
