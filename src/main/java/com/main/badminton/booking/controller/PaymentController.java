package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.PaymentRequestDTO;
import com.main.badminton.booking.dto.response.PaymentResponseDTO;
import com.main.badminton.booking.dto.response.ResponseObject;
import com.main.badminton.booking.dto.response.SimplePaymentResponseDTO;
import com.main.badminton.booking.dto.vnpay.PaymentDTO;
import com.main.badminton.booking.entity.BookingOrders;
import com.main.badminton.booking.entity.Payments;
import com.main.badminton.booking.repository.BookingOrdersRepository;
import com.main.badminton.booking.service.interfc.BookingOrdersService;
import com.main.badminton.booking.service.interfc.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BookingOrdersService bookingOrdersService;

    @GetMapping("/user/{userId}")
    public List<PaymentResponseDTO> getPaymentsByUserId(@PathVariable Integer userId) {
        return paymentService.getPaymentsByUserId(userId);
    }
    @GetMapping("/yard/{yardId}")
    public List<SimplePaymentResponseDTO> getPaymentsByYardId(@PathVariable Integer yardId) {
        return paymentService.getPaymentsByYardId(yardId);
    }
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        List<PaymentResponseDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable Integer id, @RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentResponseDTO updatedPayment = paymentService.updatePayment(id, paymentRequestDTO);
        return ResponseEntity.ok(updatedPayment);
    }

    @GetMapping("/vn-pay")
    public ResponseObject<PaymentDTO> pay(HttpServletRequest request) {
        return new ResponseObject<>(HttpStatus.OK, "Success", paymentService.createVnPayPayment(request));
    }
    @GetMapping("/vn-pay-callback")
    public void payCallbackHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String status = request.getParameter("vnp_ResponseCode");
        String bookingCodeStr = request.getParameter("vnp_Data");
        String amount = request.getParameter("vnp_Amount");
        if (status.equals("00") && !bookingCodeStr.isEmpty() && !amount.isEmpty()) {
            Integer bookingCode = Integer.valueOf(bookingCodeStr);
            BookingOrders bookingOrders = bookingOrdersService.updateStatus(bookingCode);
            if(bookingOrders != null){
                Payments payments = new Payments();
                payments.setFinalPrice(Double.valueOf(amount));
                payments.setBookingOrders(bookingOrders);
                payments.setIStournament(true);
                paymentService.savePayment(payments);
            }
            response.sendRedirect("http://localhost:5173/payment-success");// to done page
        } else {
            response.sendRedirect("http://localhost:5173/SuccessPayment");// to done page
        }
    }
}
