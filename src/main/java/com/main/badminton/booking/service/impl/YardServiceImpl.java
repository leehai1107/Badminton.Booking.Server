package com.main.badminton.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public List<Yards> getAllYards() {
        return yardRepository.findAll().stream().toList();
    }

    @Override
    public List<Yards> getAllYardsByActiveStatus() {
        return yardRepository.findAllByActiveStatus().stream().toList();
    }
    
}
