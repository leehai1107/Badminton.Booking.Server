package com.main.badminton.booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDTO {
    private String username;
    private String email;
    private String password;
    private Boolean gender;
    private Date dob;
    private String firstName;
    private String lastName;
    private int roleId;
    private int managerId;
}
