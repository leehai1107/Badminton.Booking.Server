package com.main.badminton.booking.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private Boolean status;
    private Boolean gender;
    private Date dob;
    private String firstName;
    private String lastName;
}
