package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.FeedBacks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBacks, Integer> {
}
