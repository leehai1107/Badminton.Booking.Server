package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.ChangePasswordRequest;
import com.main.badminton.booking.dto.request.UserRequestDTO;
import com.main.badminton.booking.dto.response.UserResponseDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    UserResponseDTO getUserById(Integer id);
    UserResponseDTO getUserByUsername(String username);
    UserResponseDTO getUserByEmail(String email);
    List<UserResponseDTO> searchUsers(String keyword);

    void changePassword(ChangePasswordRequest request, Principal connectedUser);
}
