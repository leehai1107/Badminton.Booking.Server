package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.request.FeedbackRequestDTO;
import com.main.badminton.booking.dto.response.FeedbackResponseDTO;
import com.main.badminton.booking.entity.FeedBacks;
import com.main.badminton.booking.entity.Payments;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.PaymentRepository;
import com.main.badminton.booking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeedbackConverter {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepo userRepository;

    public FeedBacks toEntity(FeedbackRequestDTO dto) {
        Payments payment = paymentRepository.findById(dto.getPaymentId()).orElse(null);
        User user = userRepository.findById(dto.getUserId()).orElse(null);

        return FeedBacks.builder()
                .rating(dto.getRating())
                .problem(dto.getProblem())
                .payments(payment)
                .user(user)
                .build();
    }

    public FeedbackResponseDTO toDTO(FeedBacks entity) {
        return FeedbackResponseDTO.builder()
                .id(entity.getId())
                .rating(entity.getRating())
                .problem(entity.getProblem())
                .createAt(entity.getCreateAt())
                .createDate(entity.getCreateDate())
                .updateDate(entity.getUpdateDate())
                .createBy(entity.getCreateBy())
                .updateBy(entity.getUpdateBy())
                .paymentId(entity.getPayments().getId())
                .userId(entity.getUser().getId())
                .build();
    }
}
