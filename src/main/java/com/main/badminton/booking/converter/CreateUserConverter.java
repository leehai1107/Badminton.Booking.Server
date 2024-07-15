package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.CreateUserRequestDTO;
import com.main.badminton.booking.dto.response.CreateUserResponseDTO;
import com.main.badminton.booking.entity.User;
import org.springframework.stereotype.Component;


@Component
public class CreateUserConverter {
    public User convertToEntity(CreateUserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setGender(userRequestDTO.getGender());
        user.setDob(userRequestDTO.getDob());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        return user;
    }

    public CreateUserResponseDTO convertToDTO(User user) {
        return new CreateUserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getGender(),
                user.getDob(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreateDate(),
                user.getUpdateDate()
        );
    }
}
