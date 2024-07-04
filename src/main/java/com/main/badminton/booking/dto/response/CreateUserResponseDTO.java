package com.main.badminton.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private Boolean gender;
    private Date dob;
    private String firstName;
    private String lastName;
    private LocalDate createDate;
    private LocalDate updateDate;
}
