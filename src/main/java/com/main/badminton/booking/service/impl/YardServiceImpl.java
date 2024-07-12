package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.converter.TelephonesConverter;
import com.main.badminton.booking.converter.YardConverter;
import com.main.badminton.booking.dto.TelephonesDTO;
import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.entity.Telephones;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.entity.Yards;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.repository.YardRepository;
import com.main.badminton.booking.service.interfc.TelephonesService;
import com.main.badminton.booking.service.interfc.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final TelephonesConverter telephonesConverter;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TelephonesService telephonesService;

    @Autowired
    public YardServiceImpl(YardRepository yardRepository, YardConverter yardConverter, TelephonesConverter telephonesConverter) {
        this.yardRepository = yardRepository;
        this.yardConverter = yardConverter;
        this.telephonesConverter = telephonesConverter;
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
    public List<YardResponseDTO> getRandomYard() {
        List<Yards> list = yardRepository.findRandomActiveYards();
        return list
                .stream()
                .map(yardConverter::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public YardResponseDTO addTelephonesToYard(Integer yardId, List<String> telephones) {
        Yards yards = yardRepository.findById(yardId).orElse(null);
        if (yards == null) {
            return null;
        }

        List<TelephonesDTO> newTelephonesDTOS = new ArrayList<>();
        List<Telephones> allTelephones = new ArrayList<>();

        for (String tele : telephones) {
            var existTelephones = telephonesService.getByTelephone(tele).stream()
                    .map(telephonesConverter::toEntity)
                    .collect(Collectors.toList());

            if (existTelephones.isEmpty()) {
                // If telephone does not exist, create a new one
                TelephonesDTO telephonesDTO = new TelephonesDTO();
                telephonesDTO.setTelephone(tele);
                telephonesDTO.setYardId(yardId);
                telephonesService.create(telephonesDTO);

//                newTelephonesDTOS.add(telephonesDTO);
            } else {
                // Handle existing telephones
                for (Telephones t : existTelephones) {
                    if (t.getYards() == null) {
                        // If the telephone exists but does not have a yard, associate it with the current yard
                        TelephonesDTO telephonesDTO = telephonesConverter.toDTO(t);
                        telephonesDTO.setYardId(yardId);
                        telephonesService.update(telephonesDTO.getId(), telephonesDTO);
                        newTelephonesDTOS.add(telephonesDTO);
                    } else if (t.getYards().getId().equals(yardId)) {
                        // If the telephone is already associated with the current yard, just add it to the list
                        newTelephonesDTOS.add(telephonesConverter.toDTO(t));
                    }
                    // If the telephone is associated with another yard, skip it
                }
            }
        }

        // Convert new DTOs to entities
        List<Telephones> newTelephones = newTelephonesDTOS.stream()
                .map(telephonesConverter::toEntity)
                .collect(Collectors.toList());

        // Add all telephones (existing + new) to the yard
        allTelephones.addAll(newTelephones);
        yards.setTelephones(allTelephones);

        // Save the yard
        yardRepository.save(yards);

        return yardConverter.convertToDTO(yards);
    }

}
