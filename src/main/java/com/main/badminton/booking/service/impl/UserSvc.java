package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.dto.request.UserDTO;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.service.interfc.IUserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class UserSvc implements IUserSvc {

    @Autowired
    private UserRepo userRepo;

    @Override
    public void update(Integer id, UserDTO userDTO) throws BadCredentialsException{
        User userDB = userRepo.findById(id).get();
        if(userDB != null){
            userDB.setDob(userDTO.getDob());
            userDB.setStatus(userDTO.getStatus());
            userDB.setGender(userDTO.getGender());
            userDB.setFirstName(userDTO.getFirstName());
            userDB.setLastName(userDTO.getLastName());
            userRepo.save(userDB);
        }
    }
}
