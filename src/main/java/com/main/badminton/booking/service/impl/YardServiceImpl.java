package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.YardConverter;
import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.entity.Yards;
import com.main.badminton.booking.repository.YardRepository;
import com.main.badminton.booking.service.interfc.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class YardServiceImpl implements YardService {

    @Autowired
    private YardRepository yardRepository;

    @Autowired
    private YardConverter yardConverter;

    public YardResponseDTO createYard(YardRequestDTO request) {
        if (request.getOpenTime().isAfter(request.getCloseTime())) {
            throw new IllegalArgumentException("Open time must be before close time");
        }

        Yards yard = yardConverter.toEntity(request);
        yard = yardRepository.save(yard);
        return yardConverter.toResponseDTO(yard);
    }

    @Override
    public List<Integer> getProvinceIds() {
        return yardRepository.findAll()
                .stream()
                .map(Yards::getProvinceId)
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public YardResponseDTO getYardById(Integer id) {
        return yardRepository.findById(id)
                .map(yardConverter::toResponseDTO)
                .orElse(null);
    }
}
