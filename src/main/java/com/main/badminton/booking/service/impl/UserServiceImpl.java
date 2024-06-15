package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.UserConverter;
import com.main.badminton.booking.dto.request.ChangePasswordRequest;
import com.main.badminton.booking.dto.request.UserDTO;
import com.main.badminton.booking.dto.response.UserResponseDTO;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.service.interfc.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserConverter userConverter;


    private final PasswordEncoder passwordEncoder;


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
    public List<UserResponseDTO> searchUsers(String keyword) {
        List<User> users = userRepo.findByKeyword(keyword);
        return users.stream()
                .map(userConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        //check ì the current password is correct

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }

        //check if the 2 new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        //update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
         //save new password
        userRepo.save(user);
    }
    @Override
    public UserResponseDTO updateUserInfo(Integer id, UserDTO userDTO) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Update the fields except for password and status
            user.setGender(userDTO.getGender());
            user.setDob(userDTO.getDob());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            userRepo.save(user);
            return userConverter.convertToDto(user);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }
    @Override
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepo.findAll(pageable);
        return users.map(userConverter::convertToDto);
    }
}
