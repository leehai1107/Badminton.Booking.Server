package com.main.badminton.booking.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class YardRequestDTO {
    private String name;
    private String address;
    private Integer provinceId;
    private String description;
    private Boolean status;
    private LocalDate openTime;
    private LocalDate closeTime;
    private Integer hostId;
}
