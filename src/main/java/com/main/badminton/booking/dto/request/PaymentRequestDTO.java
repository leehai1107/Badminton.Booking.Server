package com.main.badminton.booking.dto.request;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Double finalPrice;
    private Boolean iStournament;
    private Integer bookingOrderId;
}
