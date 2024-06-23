package com.main.badminton.booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingOrdersRequestDTO {
    private Integer yardId;
    private Integer userId;
    private Integer scheduleId;
    private Integer slotId;
    private LocalDate tournamentStart;
    private LocalDate tournamentEnd;
}
