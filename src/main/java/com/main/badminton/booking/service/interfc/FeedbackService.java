package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.FeedbackRequestDTO;
import com.main.badminton.booking.dto.response.FeedbackResponseDTO;

public interface FeedbackService {
    FeedbackResponseDTO createFeedback(FeedbackRequestDTO requestDTO);
}
