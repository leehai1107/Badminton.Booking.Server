package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.PaymentRequestDTO;
import com.main.badminton.booking.dto.response.PaymentResponseDTO;
import com.main.badminton.booking.dto.response.PaymentUserResponse;
import com.main.badminton.booking.dto.response.ResponseObject;
import com.main.badminton.booking.dto.response.SimplePaymentResponseDTO;
import com.main.badminton.booking.dto.vnpay.PaymentDTO;
import com.main.badminton.booking.entity.BookingOrders;
import com.main.badminton.booking.entity.Payments;
import com.main.badminton.booking.entity.YardCheckins;
import com.main.badminton.booking.service.interfc.BookingOrdersService;
import com.main.badminton.booking.service.interfc.PaymentService;
import com.main.badminton.booking.service.interfc.YardCheckInService;
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
    @Autowired
    private YardCheckInService yardCheckInService;

    @GetMapping("/user/{userId}")
    public List<PaymentUserResponse> getPaymentsByUserId(@PathVariable Integer userId) {
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
            if (bookingOrders != null) {
                Payments payments = new Payments();
                payments.setFinalPrice(Double.valueOf(amount));
                payments.setBookingOrders(bookingOrders);
                payments.setIStournament(true);
                Payments payment = paymentService.savePayment(payments);
                System.out.println("Payment saved: " + payment);

                YardCheckins yardCheckins = new YardCheckins();
                yardCheckins.setStatus(false);
                yardCheckins.setPayments(payment);
                yardCheckins.setUser(bookingOrders.getUser());
                yardCheckInService.saveCheckIns(yardCheckins);
            }
            response.sendRedirect("http://localhost:5173/payment-success"); // to done page
        } else {
            Integer bookingCode = Integer.valueOf(bookingCodeStr);
            bookingOrdersService.updateStatusToFalse(bookingCode);
            response.sendRedirect("http://localhost:5173/payment-error"); // to done page
        }
    }
}
