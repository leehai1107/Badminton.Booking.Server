package com.main.badminton.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingOrdersResponseDTO {
    private Integer id;
    private LocalDate bookingAt;
    private Boolean status;
    private Integer yardId;
    private Integer userId;
    private Integer slotId;
    private LocalDate tournamentStart;
    private LocalDate tournamentEnd;
}
