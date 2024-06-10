package com.main.badminton.booking.service.impl;

import java.util.List;

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
    @Value("${page.size}")
    public int pageSize;
    @Override
    public List<Yards> getAllYards(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return yardRepository.findAll(page).stream().toList();
    }

    @Override
    public List<Yards> getAllYardsByActiveStatus(int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return yardRepository.findAllByActiveStatus(page).stream().toList();
    }

    @Override
    public List<Yards> getYardsByName(String name, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return yardRepository.findYardByName(name, page);
    }
}
