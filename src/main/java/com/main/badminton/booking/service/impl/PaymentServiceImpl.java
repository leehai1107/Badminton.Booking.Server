package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.config.VNPAYConfig;
import com.main.badminton.booking.converter.PaymentConverter;
import com.main.badminton.booking.dto.request.PaymentRequestDTO;
import com.main.badminton.booking.dto.response.PaymentResponseDTO;
import com.main.badminton.booking.dto.vnpay.PaymentDTO;
import com.main.badminton.booking.entity.BookingOrders;
import com.main.badminton.booking.entity.Payments;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.BookingOrdersRepository;
import com.main.badminton.booking.repository.PaymentRepository;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.service.interfc.PaymentService;
import com.main.badminton.booking.utils.VNPAY.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PaymentConverter paymentConverter;
    @Autowired
    private BookingOrdersRepository bookingOrdersRepository;

    private final VNPAYConfig vnPayConfig;

    @Override
    public List<PaymentResponseDTO> getPaymentsByUserId(Integer userId) {
        return paymentRepository.findByBookingOrders_User_Id(userId)
                .stream()
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

    @Override
    public void savePayment(Payments payments) {
        paymentRepository.save(payments);
    }

    @Override
    public PaymentDTO createVnPayPayment(HttpServletRequest request) {
        long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
        String bankCode = request.getParameter("bankCode");
        String bookingCode = request.getParameter("bookingCode");
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig(bookingCode);
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));
        //build query url
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return PaymentDTO.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl).build();
    }
}
