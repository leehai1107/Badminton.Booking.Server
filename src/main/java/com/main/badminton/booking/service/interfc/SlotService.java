package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.SlotRequestDTO;
import com.main.badminton.booking.dto.response.SlotResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;
import java.util.List;

public interface SlotService {
    public String createSlot(Integer yardId, SlotRequestDTO slotRequestDTO);

    public SlotRequestDTO updateSlot(Integer slotId, SlotRequestDTO slotRequestDTO);

    List<SlotResponseDTO> getSlotsByYardId(Integer yardId, int pageNumber);
}
