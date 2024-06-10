package com.main.badminton.booking.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.main.badminton.booking.converter.YardConverter;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.badminton.booking.entity.Yards;
import com.main.badminton.booking.service.interfc.IYardService;
import com.main.badminton.booking.utils.wapper.API;

import lombok.RequiredArgsConstructor;


@RequestMapping("api/v1/yards")
@RequiredArgsConstructor
@PermitAll
@RestController
public class YardController {

    @Autowired
    private final YardConverter yardConverter;
    @Autowired
    private final IYardService yardService;

    @GetMapping()
    public API.Response<List<YardResponseDTO>> getAllYards(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber){
        List<Yards> yards = yardService.getAllYards(pageNumber);
        List<YardResponseDTO> yardResponseDTOS = yards
                .stream()
                .map(yardConverter::toYardResponseDTO)
                .toList();
        return API.Response.success(yardResponseDTOS);
    }

    @GetMapping("/active")
    public API.Response<List<YardResponseDTO>> getAllActiveYards(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber){
        List<Yards> yards = yardService.getAllYardsByActiveStatus(pageNumber);
        List<YardResponseDTO> yardResponseDTOS = yards
                .stream()
                .map(yardConverter::toYardResponseDTO)
                .toList();
        return API.Response.success(yardResponseDTOS);
    }

    @GetMapping("/search")
    public API.Response<List<YardResponseDTO>> getYardByName(@RequestParam(name = "name") String name,
                                                   @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber){
        List<Yards> yards = yardService.getYardsByName(name, pageNumber);
        List<YardResponseDTO> yardResponseDTOS = yards
                .stream()
                .map(yardConverter::toYardResponseDTO)
                .toList();
        return API.Response.success(yardResponseDTOS);
    }
}
