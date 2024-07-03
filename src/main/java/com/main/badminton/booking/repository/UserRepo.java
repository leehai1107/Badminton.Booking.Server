package com.main.badminton.booking.repository;

import com.main.badminton.booking.entity.Role;
import com.main.badminton.booking.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String userEmail);

    @Query(value = "SELECT * FROM badmintonswd.user WHERE email = :userEmail", nativeQuery = true)
    User findByEmail2(String userEmail);
//    @Query("SELECT u FROM User u where u.username = :username")
    Optional<User> findByUsername(String username);
    User findByRole(Role role);
    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword% OR u.email LIKE %:keyword% OR u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword%")
    List<User> findByKeyword(String keyword);
    @Query("SELECT u FROM User u WHERE u.manager.id =:managerId")
    Page<User> GetStaffsByManager(Integer managerId, Pageable pageable);
}
