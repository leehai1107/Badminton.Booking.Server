package com.main.badminton.booking.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FeedbackResponseDTO {
    private Integer id;
    private Float rating;
    private String problem;
    private String createAt;
    private LocalDate createDate;
    private LocalDate updateDate;
    private Integer createBy;
    private Integer updateBy;
    private Integer paymentId;
    private Integer userId;
}
