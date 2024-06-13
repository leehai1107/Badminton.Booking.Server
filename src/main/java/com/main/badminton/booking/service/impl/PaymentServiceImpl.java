package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.PaymentConverter;
import com.main.badminton.booking.dto.response.PaymentResponseDTO;
import com.main.badminton.booking.entity.Payments;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.PaymentRepository;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.service.interfc.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PaymentConverter paymentConverter;

    @Override
    public List<PaymentResponseDTO> getPaymentsByUserId(Integer userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return null; // or throw an exception
        }
        List<Payments> payments = paymentRepository.findByBookingOrders_User(user);
        return payments.stream()
                .map(paymentConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
