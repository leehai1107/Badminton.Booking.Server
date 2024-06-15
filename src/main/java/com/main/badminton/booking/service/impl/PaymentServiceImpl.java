package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.PaymentConverter;
import com.main.badminton.booking.dto.request.PaymentRequestDTO;
import com.main.badminton.booking.dto.response.PaymentResponseDTO;
import com.main.badminton.booking.entity.BookingOrders;
import com.main.badminton.booking.entity.Payments;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.BookingOrdersRepository;
import com.main.badminton.booking.repository.PaymentRepository;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.service.interfc.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PaymentConverter paymentConverter;
    @Autowired
    private BookingOrdersRepository bookingOrdersRepository;

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
    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        List<Payments> paymentsList = paymentRepository.findAll();
        return paymentsList.stream()
                .map(paymentConverter::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public PaymentResponseDTO updatePayment(Integer id, PaymentRequestDTO paymentRequestDTO) {
        Optional<Payments> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            Payments payment = optionalPayment.get();
            payment.setFinalPrice(paymentRequestDTO.getFinalPrice());
            payment.setIStournament(paymentRequestDTO.getIStournament());
            if (paymentRequestDTO.getBookingOrderId() != null) {
                Optional<BookingOrders> bookingOrder = bookingOrdersRepository.findById(paymentRequestDTO.getBookingOrderId());
                bookingOrder.ifPresent(payment::setBookingOrders);
            }
            paymentRepository.save(payment);
            return paymentConverter.convertToDto(payment);
        } else {
            throw new RuntimeException("Payment not found with id " + id);
        }
    }
}
