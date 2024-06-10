package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.Role;
import com.main.badminton.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String userEmail);
    Optional<User> findByUsername(String username);
    User findByRole(Role role);
}