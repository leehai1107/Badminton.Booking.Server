package com.main.badminton.booking.converter;

import com.main.badminton.booking.dto.response.SimpleFeedBackResponseDTO;
import com.main.badminton.booking.entity.FeedBacks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleFeedBackConverter {

    @Autowired
    private ModelMapper modelMapper;

    public SimpleFeedBackResponseDTO entityToDTO(FeedBacks feedBacks) {
        SimpleFeedBackResponseDTO dto = modelMapper.map(feedBacks, SimpleFeedBackResponseDTO.class);

        // Map user id and payment id if available
        if (feedBacks.getUser() != null) {
            dto.setUserId(feedBacks.getUser().getId());
        }

        if (feedBacks.getPayments() != null) {
            dto.setPaymentId(feedBacks.getPayments().getId());
        }

        // Map yard related fields
        if (feedBacks.getPayments() != null &&
                feedBacks.getPayments().getBookingOrders() != null &&
                feedBacks.getPayments().getBookingOrders().getYards() != null) {

            dto.setYardId(feedBacks.getPayments().getBookingOrders().getYards().getId());
            dto.setYardName(feedBacks.getPayments().getBookingOrders().getYards().getName());
            dto.setYardHostId(feedBacks.getPayments().getBookingOrders().getYards().getHost() != null ?
                    feedBacks.getPayments().getBookingOrders().getYards().getHost().getId() : null);
            dto.setYardStatus(feedBacks.getPayments().getBookingOrders().getYards().getStatus());
        }

        return dto;
    }
}
