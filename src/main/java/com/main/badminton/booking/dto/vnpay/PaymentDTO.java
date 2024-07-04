package com.main.badminton.booking.dto.vnpay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
        private String code;
        private String message;
        private String paymentUrl;
}
