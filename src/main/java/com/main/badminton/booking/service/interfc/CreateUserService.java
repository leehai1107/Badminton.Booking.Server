package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.CreateUserRequestDTO;
import com.main.badminton.booking.dto.response.CreateUserResponseDTO;

public interface CreateUserService {
    CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO);
}
