package com.main.badminton.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingOrderUserResponse {
    private SlotUserResponse slot;
    private LocalDate tournamentStart;
    private LocalDate tournamentEnd;
    private Integer user_id;
    private YardUserResponse yard;
}
