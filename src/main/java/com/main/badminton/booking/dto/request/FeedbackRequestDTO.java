package com.main.badminton.booking.dto.request;

import lombok.Data;

@Data
public class FeedbackRequestDTO {
    private Float rating;
    private String problem;
    private Integer paymentId;
    private Integer userId;
}
