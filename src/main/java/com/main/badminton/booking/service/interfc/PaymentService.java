package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.response.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentResponseDTO> getPaymentsByUserId(Integer userId);
}
