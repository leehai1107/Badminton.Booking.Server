package com.main.badminton.booking.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class YardRequestDTO {
    private String name;
    private String address;
    private Integer provinceId;
    private String description;
    private Boolean status;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private Integer hostId;
}
