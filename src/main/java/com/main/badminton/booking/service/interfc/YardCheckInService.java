package com.main.badminton.booking.service.interfc;

import com.main.badminton.booking.entity.YardCheckins;
import java.util.List;
public interface YardCheckInService {
    public List<YardCheckins> findAllYardCheckIns();
}
