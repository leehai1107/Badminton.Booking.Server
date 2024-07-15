package com.main.badminton.booking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentUserResponse {
    private Integer id;
    private Double finalPrice;
    private BookingOrderUserResponse booking_order;
    private CheckInUserResponse checkin;
}
