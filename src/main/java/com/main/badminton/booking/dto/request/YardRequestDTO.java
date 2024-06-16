package com.main.badminton.booking.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YardRequestDTO {
    private String name;
    private String address;
    private Integer provinceId;
    private String description;
    private Boolean status;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Integer hostId;
}
