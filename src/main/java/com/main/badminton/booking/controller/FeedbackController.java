package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.FeedbackRequestDTO;
import com.main.badminton.booking.dto.response.FeedbackResponseDTO;
import com.main.badminton.booking.dto.response.SimpleFeedBackResponseDTO;
import com.main.badminton.booking.service.interfc.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/create")
    public ResponseEntity<FeedbackResponseDTO> createFeedback(@RequestBody FeedbackRequestDTO requestDTO) {
        FeedbackResponseDTO responseDTO = feedbackService.createFeedback(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @GetMapping
    public ResponseEntity<List<SimpleFeedBackResponseDTO>> getAllFeedbacks() {
        List<SimpleFeedBackResponseDTO> feedbacks = feedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    // Get all feedbacks of a yard
    @GetMapping("/yard/{yardId}")
    public ResponseEntity<List<SimpleFeedBackResponseDTO>> getAllFeedbacksByYardId(@PathVariable("yardId") Integer yardId) {
        List<SimpleFeedBackResponseDTO> feedbacks = feedbackService.getAllFeedbacksByYardId(yardId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
}
