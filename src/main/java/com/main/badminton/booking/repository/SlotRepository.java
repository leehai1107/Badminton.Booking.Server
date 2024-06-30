package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.Slots;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slots, Integer> {
    @Query(value = "SELECT u FROM Slots u WHERE u.yards.id =:yardId")
    List<Slots> findByYardId(@Param("yardId") Integer yardId, Pageable pageable);
}
