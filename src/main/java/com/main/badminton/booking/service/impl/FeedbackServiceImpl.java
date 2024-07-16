package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.FeedbackConverter;
import com.main.badminton.booking.converter.SimpleFeedBackConverter;
import com.main.badminton.booking.dto.request.FeedbackRequestDTO;
import com.main.badminton.booking.dto.response.FeedbackResponseDTO;
import com.main.badminton.booking.dto.response.SimpleFeedBackResponseDTO;
import com.main.badminton.booking.entity.FeedBacks;
import com.main.badminton.booking.repository.FeedbackRepository;
import com.main.badminton.booking.service.interfc.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private FeedbackConverter feedbackConverter;

    @Autowired
    private SimpleFeedBackConverter simpleFeedBackConverter;

    @Override
    public FeedbackResponseDTO createFeedback(FeedbackRequestDTO requestDTO) {
        FeedBacks feedback = feedbackConverter.toEntity(requestDTO);
        FeedBacks savedFeedback = feedbackRepository.save(feedback);
        return feedbackConverter.toDTO(savedFeedback);
    }

    @Override
    public List<SimpleFeedBackResponseDTO> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(simpleFeedBackConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimpleFeedBackResponseDTO> getAllFeedbacksByYardId(Integer id) {
        return feedbackRepository.findByYardId(id).stream()
                .map(simpleFeedBackConverter::entityToDTO)
                .collect(Collectors.toList());
    }
}
