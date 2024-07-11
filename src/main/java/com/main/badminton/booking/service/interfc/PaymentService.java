package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.PaymentRequestDTO;
import com.main.badminton.booking.dto.response.PaymentResponseDTO;
import com.main.badminton.booking.dto.response.SimplePaymentResponseDTO;
import com.main.badminton.booking.dto.vnpay.PaymentDTO;
import com.main.badminton.booking.entity.Payments;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PaymentService {
    List<PaymentResponseDTO> getPaymentsByUserId(Integer userId);
    List<PaymentResponseDTO> getAllPayments();
    PaymentResponseDTO updatePayment(Integer id, PaymentRequestDTO paymentRequestDTO);
    void savePayment(Payments payments);
    PaymentDTO createVnPayPayment(HttpServletRequest request);
    List<SimplePaymentResponseDTO> getPaymentsByYardId(Integer yardId);
}
