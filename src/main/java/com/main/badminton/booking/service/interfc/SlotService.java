package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.request.SlotRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

public interface SlotService {
    public String createSlot(Integer yardId, SlotRequestDTO slotRequestDTO);

    public SlotRequestDTO updateSlot(Long slotId, SlotRequestDTO slotRequestDTO);
}
