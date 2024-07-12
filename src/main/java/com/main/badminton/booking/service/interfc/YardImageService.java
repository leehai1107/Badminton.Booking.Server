package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.response.YardImageDTO;
import com.main.badminton.booking.dto.request.YardImagesDTO;

import java.util.List;
public interface YardImageService {
    void createYardImage(YardImagesDTO yardImagesDTO);

    List<YardImageDTO> findAllByYard_Id(Integer yardId);
}
