package com.main.badminton.booking.dto.response;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SlotResponseDTO {
    private Integer id;

    private Double price;

    private String status;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate createDate;

    private LocalDate updateDate;

    private Integer createBy;

    private Integer updateBy;
}
