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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class YardServiceImpl implements YardService {

    @Autowired
    private final YardRepository yardRepository;

    @Autowired
    private final YardConverter yardConverter;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    public YardServiceImpl(YardRepository yardRepository, YardConverter yardConverter) {
        this.yardRepository = yardRepository;
        this.yardConverter = yardConverter;
    }
    @Override
    public YardResponseDTO createYard(YardRequestDTO yardRequestDTO) {
        Yards yard = yardConverter.convertToEntity(yardRequestDTO);

        User host = userRepo.findById(yardRequestDTO.getHostId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid host ID"));
        yard.setHost(host);

        Yards savedYard = yardRepository.save(yard);
        return yardConverter.convertToDTO(savedYard);
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

    @Value("${page.size}")
    public int pageSize;
    @Override
    public List<YardResponseDTO> getAllYards(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Yards> yards = yardRepository.findAll(page).stream().toList();
        return yards
                .stream()
                .map(yardConverter::toResponseDTO)
                .toList();
    }

    @Override
    public List<YardResponseDTO> getAllYardsByActiveStatus(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Yards> yards = yardRepository.findAllByActiveStatus(page);
        return yards
                .stream()
                .map(yardConverter::toResponseDTO)
                .toList();
    }

    @Override
    public List<YardResponseDTO> getYardsByName(String name, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Yards> yards = yardRepository.findYardByName(name, page);
        return yards
                .stream()
                .map(yardConverter::toResponseDTO)
                .toList();
    }

    @Override
    public List<YardResponseDTO> getAllYardsByHostId(Integer hostId) {
        List<Yards> yards = yardRepository.findAllByHostId(hostId); // Assuming you have a method in your repository to find yards by hostId
        return yards.stream()
                .map(yardConverter::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public YardResponseDTO getYardDetailActiveSlots(Integer yardId) {
        return yardRepository.getYardDetailActiveSlots(yardId)
                .map(yardConverter::convertToDTO)
                .orElse(null);
    }
    @Override
    public List<YardResponseDTO> getRandomYard(Integer numberRandom) {
        List<Yards> list = yardRepository.findRandomActiveYards(numberRandom);
        return list
                .stream()
                .map(yardConverter::toResponseDTO)
                .collect(Collectors.toList());
    }
}
