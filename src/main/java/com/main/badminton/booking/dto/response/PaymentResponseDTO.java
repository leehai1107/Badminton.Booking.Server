package com.main.badminton.booking.dto.response;

import lombok.Data;

@Data
public class PaymentResponseDTO {
    private Integer id;
    private Double finalPrice;
    private Boolean iStournament;
    private Integer bookingOrderId;
}
