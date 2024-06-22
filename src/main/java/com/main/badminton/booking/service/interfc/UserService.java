package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.ChangePasswordRequest;
import com.main.badminton.booking.dto.request.UserDTO;
import com.main.badminton.booking.dto.request.UserRequestDTO;
import com.main.badminton.booking.dto.response.UserResponseDTO;
import com.main.badminton.booking.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();
    UserResponseDTO getUserById(Integer id);
    UserResponseDTO getUserByUsername(String username);
    UserResponseDTO getUserByEmail(String email);
    List<UserResponseDTO> searchUsers(String keyword);
    User getUserByEmail2(String email);
    void changePassword(ChangePasswordRequest request, Principal connectedUser);
    UserResponseDTO updateUserInfo(Integer id, UserDTO userDTO);
    Page<UserResponseDTO> getAllUsers(Pageable pageable);
}
