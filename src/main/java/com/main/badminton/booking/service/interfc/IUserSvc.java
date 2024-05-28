package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.UserDTO;
import org.springframework.security.authentication.BadCredentialsException;

public interface IUserSvc {
    void update(Integer id, UserDTO userDTO) throws BadCredentialsException;

}
