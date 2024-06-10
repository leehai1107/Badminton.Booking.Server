// src/main/java/com/main/badminton/booking/converter/UserConverter.java
package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.UserRequestDTO;
import com.main.badminton.booking.dto.response.UserResponseDTO;
import com.main.badminton.booking.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDTO convertToDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public User convertToEntity(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO, User.class);
    }
}
