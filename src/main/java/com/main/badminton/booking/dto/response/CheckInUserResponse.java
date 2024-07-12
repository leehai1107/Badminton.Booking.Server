package com.main.badminton.booking.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInUserResponse {
    private Integer id;
    private LocalTime checkIn_time;

    private LocalTime checkOut_time;

    private Boolean status;
}
