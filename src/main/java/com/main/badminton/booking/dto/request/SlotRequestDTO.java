package com.main.badminton.booking.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SlotRequestDTO {
    private Double price;

    private Boolean status;

    private String start_time;

    private String end_time;
}
