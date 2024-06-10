package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.YardConverter;
import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.entity.Yards;
import com.main.badminton.booking.repository.UserRepo;
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

    @Autowired
    public YardServiceImpl(YardRepository yardRepository, YardConverter yardConverter) {
        this.yardRepository = yardRepository;
        this.yardConverter = yardConverter;
    }

    @Override
    public void createYard(YardRequestDTO requestDTO) {
        Yards yard = yardConverter.toEntity(requestDTO);
        yardRepository.save(yard);
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
    public YardResponseDTO updateYard(Integer id, YardRequestDTO yardRequestDTO) {
        Yards yard = yardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Yard not found"));

        yardConverter.updateEntity(yardRequestDTO, yard);

        Yards updatedYard = yardRepository.save(yard);
        return yardConverter.toResponseDTO(updatedYard);
    }
}
