package com.main.badminton.booking.service.interfc;

import java.util.List;

import com.main.badminton.booking.entity.Yards;

public interface IYardService {
    List<Yards> getAllYards();
    List<Yards> getAllYardsByActiveStatus();
}
