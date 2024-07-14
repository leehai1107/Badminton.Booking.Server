package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.YardImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface YardImageRepository extends JpaRepository<YardImages, Long> {

    @Query("SELECT yd FROM YardImages yd WHERE yd.yards.id = :yardId")
    List<YardImages> findAllByYardId(Integer yardId);
}
