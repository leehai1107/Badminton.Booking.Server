package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.config.ApplicationAuditing;
import com.main.badminton.booking.converter.SlotConverter;
import com.main.badminton.booking.dto.request.SlotRequestDTO;
import com.main.badminton.booking.dto.response.SlotResponseDTO;
import com.main.badminton.booking.entity.Slots;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.SlotRepository;
import com.main.badminton.booking.repository.UserRepo;
import com.main.badminton.booking.repository.YardRepository;
import com.main.badminton.booking.service.interfc.SlotService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SlotServiceImpl implements SlotService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private YardRepository yardRepository;

    @Autowired
    private SlotConverter slotConverter;
    @Autowired
    private ApplicationAuditing applicationAuditing;
    @Value("${page.size}")
    public int pageSize;

    @Override
    public String createSlot(Integer yardId, SlotRequestDTO slotRequestDTO) {
        User user = userRepo.findById(applicationAuditing.getCurrentAuditor().get()).orElse(null);
        if (user.getRole().getId() != 2 && user.getRole().getId() != 3) {
            Slots saved = mapToEntity(slotRequestDTO);
            saved.setYards(yardRepository.findById(yardId).get());
            slotRepository.save(saved);
            return "Create Slot successfully !!!";
        }
        return "You are not allow to create";
    }

    @Override
    public SlotRequestDTO updateSlot(Integer slotId, SlotRequestDTO slotRequestDTO) {
        User user = userRepo.findById(applicationAuditing.getCurrentAuditor().get()).orElse(null);
        if (user.getRole().getId() != 2 && user.getRole().getId() != 3) {
            Slots saved = slotRepository.findById(slotId).get();
            Slots slotMap = mapToEntity(slotRequestDTO);
            saved.setPrice(slotMap.getPrice());
            saved.setStatus(slotMap.getStatus());
            saved.setStartTime(slotMap.getStartTime());
            saved.setEndTime(slotMap.getEndTime());
            return mapToDto(slotRepository.save(saved));
        } else {
            return null;
        }
    }

    @Override
    public List<SlotResponseDTO> getSlotsByYardId(Integer yardId, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Slots> slots = slotRepository.findByYardId(yardId, pageable);
        return slots.stream().map(s -> slotConverter.toResponseDTO(s)).toList();
    }

    private SlotRequestDTO mapToDto(Slots slots){
        LocalTime startTime = slots.getStartTime();
        LocalTime endTime = slots.getEndTime();

        // Define the formatter with the desired pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Convert LocalTime to String
        String startTimeString = startTime.format(formatter);
        String endTimeString = endTime.format(formatter);

        return SlotRequestDTO.builder()
                .price(slots.getPrice())
                .status(slots.getStatus())
                .start_time(startTimeString)
                .end_time(endTimeString)
                .build();
    }

    private Slots mapToEntity(SlotRequestDTO slotRequestDTO) {
        String timeString = slotRequestDTO.getStart_time();
        String timeString2 = slotRequestDTO.getEnd_time();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime startTime = LocalTime.parse(timeString, formatter);
        LocalTime endTime = LocalTime.parse(timeString2, formatter);
        return Slots.builder()
                .price(slotRequestDTO.getPrice())
                .status(slotRequestDTO.getStatus())
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
