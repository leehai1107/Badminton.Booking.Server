package com.main.badminton.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingOrdersResponseDTO {
    private Integer yardId;
    private Integer userId;
    private Integer scheduleId;
    private Integer slotId;
    private String status;
}
