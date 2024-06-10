package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.TelephonesDTO;
import java.util.List;

public interface TelephonesService {
    TelephonesDTO create(TelephonesDTO telephonesDTO);
    TelephonesDTO update(Integer id, TelephonesDTO telephonesDTO);
    TelephonesDTO getById(Integer id);
    List<TelephonesDTO> getAll();
}
