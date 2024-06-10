package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.service.interfc.YardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/yards")
public class YardController {

    @Autowired
    private YardService yardService;

    @PostMapping
    public ResponseEntity<YardResponseDTO> createYard(@RequestBody YardRequestDTO request) {
        YardResponseDTO yardResponse = yardService.createYard(request);
        return ResponseEntity.ok(yardResponse);
    }

    @GetMapping("/province-ids")
    public ResponseEntity<List<Integer>> getProvinceIds() {
        List<Integer> provinceIds = yardService.getProvinceIds();
        return ResponseEntity.ok(provinceIds);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<YardResponseDTO> updateYard(@PathVariable Integer id, @RequestBody YardRequestDTO yardRequestDTO) {
        YardResponseDTO updatedYard = yardService.updateYard(id, yardRequestDTO);
        return ResponseEntity.ok(updatedYard);
    }
    @GetMapping("/{id}")
    public ResponseEntity<YardResponseDTO> getYardById(@PathVariable Integer id) {
        YardResponseDTO yardResponseDTO = yardService.getYardById(id);
        if (yardResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(yardResponseDTO);
    }
}
