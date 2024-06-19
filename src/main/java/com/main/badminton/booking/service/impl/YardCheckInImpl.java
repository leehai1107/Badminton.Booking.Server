package com.main.badminton.booking.service.impl;

import com.main.badminton.booking.entity.YardCheckins;
import com.main.badminton.booking.repository.YardCheckInRepository;
import com.main.badminton.booking.service.interfc.YardCheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class YardCheckInImpl implements YardCheckInService {
    @Autowired
    private YardCheckInRepository yardCheckInRepository;

    @Override
    public List<YardCheckins> findAllYardCheckIns() {
        List<YardCheckins> list = yardCheckInRepository.findAll();
        if(list != null){
            return list;
        }
        return null;
    }
}
