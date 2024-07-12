package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.Telephones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelephonesRepository extends JpaRepository<Telephones, Integer> {
    @Query("SELECT t FROM Telephones t where t.telephone=:telephone")
    List<Telephones> findByTelephones(@Param("telephone")String telephones);
}
