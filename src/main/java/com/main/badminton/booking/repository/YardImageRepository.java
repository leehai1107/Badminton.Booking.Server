package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.YardImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface YardImageRepository extends JpaRepository<YardImages, Long> {

    @Query("SELECT yd FROM YardImages yd WHERE yd.yards.id = :yardId")
    List<YardImages> findAllByYardId(Integer yardId);
    @Query("SELECT yd FROM YardImages yd WHERE yd.image=:url")
    List<YardImages> findByUrl(@Param("url") String imageUrl);
}
