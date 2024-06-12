package com.main.badminton.booking.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class YardResponseDTO {
    private Integer id;
    private String name;
    private String address;
    private Integer provinceId;
    private String description;
    private Boolean status;
    private LocalDate openTime;
    private LocalDate closeTime;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Integer createBy;
    private Integer updateBy;
}
