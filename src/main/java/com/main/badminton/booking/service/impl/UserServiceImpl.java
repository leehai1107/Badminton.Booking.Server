package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.UserConverter;
import com.main.badminton.booking.dto.request.UserRequestDTO;
import com.main.badminton.booking.dto.response.UserResponseDTO;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.service.interfc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserConverter userConverter;


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepo.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
            }
        };
    }
    @Override
    public UserResponseDTO getUserById(Integer id) {
        User user = userRepo.findById(id).orElse(null);
        return user != null ? userConverter.convertToDto(user) : null;
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepo.findByUsername(username).orElse(null);
        return user != null ? userConverter.convertToDto(user) : null;
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null ? userConverter.convertToDto(user) : null;
    }

    @Override
    public UserResponseDTO getUser(UserRequestDTO userRequestDTO) {
        User user = null;
        if (userRequestDTO.getUsername() != null) {
            user = userRepo.findByUsername(userRequestDTO.getUsername()).orElse(null);
        } else if (userRequestDTO.getEmail() != null) {
            user = userRepo.findByEmail(userRequestDTO.getEmail()).orElse(null);
        }
        return user != null ? userConverter.convertToDto(user) : null;
    }
}
