package com.main.badminton.booking.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class YardResponseDTO {
    private Integer id;
    private String name;
    private String address;
    private Integer provinceId;
    private String description;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private Integer hostId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
