package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.PaymentRequestDTO;
import com.main.badminton.booking.dto.response.PaymentResponseDTO;
import com.main.badminton.booking.dto.vnpay.PaymentDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PaymentService {
    List<PaymentResponseDTO> getPaymentsByUserId(Integer userId);
    List<PaymentResponseDTO> getAllPayments();
    PaymentResponseDTO updatePayment(Integer id, PaymentRequestDTO paymentRequestDTO);

    PaymentDTO createVnPayPayment(HttpServletRequest request);
}
