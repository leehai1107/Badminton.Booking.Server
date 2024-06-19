package com.main.badminton.booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfileDTO {
    private String userName;
    private String email;
    private Boolean gender;
    private Date dob;
    private String firstName;
    private String lastName;
}
