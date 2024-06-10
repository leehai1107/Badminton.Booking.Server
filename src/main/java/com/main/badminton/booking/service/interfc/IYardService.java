package com.main.badminton.booking.service.interfc;

import java.util.List;

import com.main.badminton.booking.entity.Yards;

public interface IYardService {
    List<Yards> getAllYards(int pageNumber);
    List<Yards> getAllYardsByActiveStatus(int pageNumber);
    List<Yards> getYardsByName(String name, int pageNumber);
}
