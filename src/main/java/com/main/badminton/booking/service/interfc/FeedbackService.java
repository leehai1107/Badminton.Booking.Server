package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.FeedbackRequestDTO;
import com.main.badminton.booking.dto.response.FeedbackResponseDTO;
import com.main.badminton.booking.dto.response.SimpleFeedBackResponseDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackResponseDTO createFeedback(FeedbackRequestDTO requestDTO);
    List<SimpleFeedBackResponseDTO> getAllFeedbacks();
}
