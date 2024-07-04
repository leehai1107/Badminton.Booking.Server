package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.CreateUserConverter;
import com.main.badminton.booking.dto.request.CreateUserRequestDTO;
import com.main.badminton.booking.dto.response.CreateUserResponseDTO;
import com.main.badminton.booking.entity.Role;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.RoleRepo;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.service.interfc.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CreateUserServiceImpl implements CreateUserService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private CreateUserConverter createUserConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO) {
        User user = createUserConverter.convertToEntity(createUserRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findById(createUserRequestDTO.getRoleId()).orElse(null));
        user.setManager(userRepository.findById(createUserRequestDTO.getManagerId()).orElse(null));
        user.setCreateDate(LocalDate.now());

        User savedUser = userRepository.save(user);

        return createUserConverter.convertToDTO(savedUser);
    }
}
