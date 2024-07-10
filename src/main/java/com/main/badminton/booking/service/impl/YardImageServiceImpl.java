package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.dto.response.YardImageDTO;
import com.main.badminton.booking.entity.YardImages;
import com.main.badminton.booking.entity.Yards;
import com.main.badminton.booking.repository.YardImageRepository;
import com.main.badminton.booking.repository.YardRepository;
import com.main.badminton.booking.service.interfc.YardImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YardImageServiceImpl implements YardImageService {

    @Autowired
    private YardRepository yardRepository;

    @Autowired
    private YardImageRepository repository;

    @Override
    public void createYardImage(String image, Integer yardId) {
        Yards yards = yardRepository.findById(yardId).get();

        YardImages yardImages = YardImages.builder()
                .image(image)
                .yards(yards)
                .build();

        repository.save(yardImages);
    }

    @Override
    public List<YardImageDTO> findAllByYard_Id(Integer yardId) {
        List<YardImages> list = repository.findAllByYardId(yardId);

        List<YardImageDTO> listDTO = list.stream().map(item -> mapToDTO(item)).toList();
        return listDTO;
    }


    private YardImageDTO mapToDTO(YardImages yardImages) {
        return YardImageDTO.builder()
                .id(yardImages.getId())
                .image(yardImages.getImage())
                .build();
    }

}
