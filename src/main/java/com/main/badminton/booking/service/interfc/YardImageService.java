package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.dto.response.YardImageDTO;
import com.main.badminton.booking.entity.YardImages;
import java.util.List;
public interface YardImageService {
    void createYardImage(String image, Integer yardId);

    List<YardImageDTO> findAllByYard_Id(Integer yardId);
}
