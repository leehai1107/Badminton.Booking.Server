package com.main.badminton.booking.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class YardCheckinResponseDTO {
    private Integer id;
    private Boolean status;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Integer paymentId;
    private Integer userId;
    private Integer checkInById;
}
