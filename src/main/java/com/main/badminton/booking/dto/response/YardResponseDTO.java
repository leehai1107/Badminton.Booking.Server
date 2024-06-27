package com.main.badminton.booking.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class YardResponseDTO {
    private Integer id;
    private String name;
    private String address;
    private Integer provinceId;
    private String description;
    private Boolean status;
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalTime createDate;
    private LocalTime updateDate;
    private Integer createBy;
    private Integer updateBy;
    private Integer hostId;
    private List<SlotResponseDTO> slots;
}
