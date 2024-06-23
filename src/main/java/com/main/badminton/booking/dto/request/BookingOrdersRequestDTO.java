package com.main.badminton.booking.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingOrdersRequestDTO {
    private String bookingAt;
    private String status;
    private Integer yardId;
    private Integer userId;
    private Integer scheduleId;
    private Integer slotId;
    private LocalTime tournamentStart;
    private LocalTime tournamentEnd;
}
