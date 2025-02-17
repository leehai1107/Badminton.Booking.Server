package com.main.badminton.booking.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private Integer roleId;
}
