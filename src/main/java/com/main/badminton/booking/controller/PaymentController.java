package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.response.PaymentResponseDTO;
import com.main.badminton.booking.service.interfc.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponseDTO>> getUserPayments(@PathVariable Integer userId) {
        List<PaymentResponseDTO> paymentResponseDTOs = paymentService.getPaymentsByUserId(userId);
        if (paymentResponseDTOs == null || paymentResponseDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paymentResponseDTOs);
    }
}
