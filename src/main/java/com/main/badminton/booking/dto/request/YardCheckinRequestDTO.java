package com.main.badminton.booking.dto.request;

import lombok.Data;

import java.time.LocalTime;

@Data
public class YardCheckinRequestDTO {
    private Integer id;
    private String status;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Integer paymentId;
    private Integer userId;
    private Integer checkInById;
}
