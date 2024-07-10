package com.main.badminton.booking.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class PaymentResponseDTO {
    private Integer id;
    private Double finalPrice;
    private Boolean iStournament;
    private Integer bookingOrderId;
    private String yardName;
    private LocalDateTime bookingAt;
    private LocalTime slotStartTime;
    private LocalTime slotEndTime;
}
