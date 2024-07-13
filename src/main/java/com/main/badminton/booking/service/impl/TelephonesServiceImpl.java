package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.TelephonesConverter;
import com.main.badminton.booking.dto.TelephonesDTO;
import com.main.badminton.booking.entity.Telephones;
import com.main.badminton.booking.repository.TelephonesRepository;
import com.main.badminton.booking.repository.YardRepository;
import com.main.badminton.booking.entity.Yards;
import com.main.badminton.booking.service.interfc.TelephonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TelephonesServiceImpl implements TelephonesService {

    @Autowired
    private TelephonesRepository telephonesRepository;

    @Autowired
    private TelephonesConverter telephonesConverter;

    @Autowired
    private YardRepository yardRepository;

    @Override
    public TelephonesDTO create(TelephonesDTO telephonesDTO) {
        Telephones telephones = telephonesConverter.toEntity(telephonesDTO);
        telephones = telephonesRepository.save(telephones);
        return telephonesConverter.toDTO(telephones);
    }

    @Override
    public TelephonesDTO update(Integer id, TelephonesDTO telephonesDTO) {
        Telephones telephones = telephonesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Telephone not found"));
        telephones.setTelephone(telephonesDTO.getTelephone());

        if (telephonesDTO.getYardId() != null) {
            Yards yards = yardRepository.findById(telephonesDTO.getYardId())
                    .orElseThrow(() -> new RuntimeException("Yard not found with id: " + telephonesDTO.getYardId()));
            telephones.setYards(yards);
        }

        telephones = telephonesRepository.save(telephones);
        return telephonesConverter.toDTO(telephones);
    }

    @Override
    public TelephonesDTO getById(Integer id) {
        Telephones telephones = telephonesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Telephone not found"));
        return telephonesConverter.toDTO(telephones);
    }

    @Override
    public List<TelephonesDTO> getAll() {
        List<Telephones> telephonesList = telephonesRepository.findAll();
        return telephonesList.stream()
                .map(telephonesConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TelephonesDTO> getByTelephone(String telephone) {
        return telephonesRepository.findByTelephones(telephone).stream()
                .map(telephonesConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTelephoneById(Integer id) {
        telephonesRepository.deleteById(id);
    }
}
