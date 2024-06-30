package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.YardCheckinRequestDTO;
import com.main.badminton.booking.dto.response.YardCheckinResponseDTO;
import com.main.badminton.booking.entity.YardCheckins;
import com.main.badminton.booking.service.interfc.YardCheckInService;
import com.main.badminton.booking.utils.wapper.API;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/checkIn")
@RequiredArgsConstructor
public class YardCheckInController {

    @Autowired
    private YardCheckInService yardCheckInService;

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAllYardCheckIn(){
        List<YardCheckins> list = yardCheckInService.findAllYardCheckIns();
        if(list != null){
            return ResponseEntity.ok(API.Response.success(list));
        } else {
            return ResponseEntity.ok(API.Response.error(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "not found"));
        }
    }

//    @PostMapping("/create")
//    public ResponseEntity<YardCheckinResponseDTO> checkIn(@RequestBody YardCheckinRequestDTO requestDTO) {
//        YardCheckinResponseDTO responseDTO = yardCheckInService.checkIn(requestDTO);
//        return ResponseEntity.ok(responseDTO);
//    }

    @PatchMapping("/status")
    public ResponseEntity<YardCheckinResponseDTO> updateStatus(@RequestBody YardCheckinRequestDTO requestDTO) {
        YardCheckinResponseDTO responseDTO = yardCheckInService.updateStatus(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
