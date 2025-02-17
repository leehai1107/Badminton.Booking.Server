package com.main.badminton.booking.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.main.badminton.booking.entity.Yards;

@Repository
public interface YardRepository extends JpaRepository<Yards, Integer> {
    List<Yards> findByOpenTimeBeforeAndCloseTimeAfter(LocalDateTime open, LocalDateTime close);
    @Query("SELECT u FROM Yards u where u.status = true")
    List<Yards> findAllByActiveStatus(Pageable pageable);
    @Query("SELECT u FROM Yards u where u.name LIKE %:name%")
    List<Yards> findYardByName(@Param("name") String name, Pageable pageable);
    List<Yards> findAllByHostId(Integer hostId);
    @Query("SELECT DISTINCT y from Yards y " +
            "LEFT JOIN FETCH y.slots s " +
            "WHERE y.id =:yardId AND "+
            "(s.status = true OR s.status IS NULL)")
    Optional<Yards> getYardDetailActiveSlots(@Param("yardId") Integer yardId);

    @Query(value = "SELECT * FROM yards WHERE status = true ORDER BY RAND() LIMIT :numberRandom", nativeQuery = true)
    List<Yards> findRandomActiveYards(Integer numberRandom);
}
