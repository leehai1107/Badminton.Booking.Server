package com.main.badminton.booking.service.impl;

import java.util.List;

import com.main.badminton.booking.converter.YardConverter;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.main.badminton.booking.entity.Yards;
import com.main.badminton.booking.repository.YardRepository;
import com.main.badminton.booking.service.interfc.IYardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YardServiceImpl implements IYardService{

    @Autowired
    private final YardRepository yardRepository;

    @Autowired
    private final YardConverter yardConverter;
    @Value("${page.size}")
    public int pageSize;
    @Override
    public List<YardResponseDTO> getAllYards(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Yards> yards = yardRepository.findAll(page).stream().toList();
        return yards
                .stream()
                .map(yardConverter::toYardResponseDTO)
                .toList();
    }

    @Override
    public List<YardResponseDTO> getAllYardsByActiveStatus(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Yards> yards = yardRepository.findAllByActiveStatus(page);
        return yards
                .stream()
                .map(yardConverter::toYardResponseDTO)
                .toList();
    }

    @Override
    public List<YardResponseDTO> getYardsByName(String name, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Yards> yards = yardRepository.findYardByName(name, page);
        return yards
                .stream()
                .map(yardConverter::toYardResponseDTO)
                .toList();
    }
}
