package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.TelephonesDTO;
import com.main.badminton.booking.service.interfc.TelephonesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telephones")
public class TelephonesController {

    @Autowired
    private TelephonesService telephonesService;

    @PostMapping
    public ResponseEntity<TelephonesDTO> create(@RequestBody TelephonesDTO telephonesDTO) {
        TelephonesDTO createdTelephone = telephonesService.create(telephonesDTO);
        return ResponseEntity.ok(createdTelephone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelephonesDTO> update(@PathVariable Integer id, @RequestBody TelephonesDTO telephonesDTO) {
        TelephonesDTO updatedTelephone = telephonesService.update(id, telephonesDTO);
        return ResponseEntity.ok(updatedTelephone);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelephonesDTO> getById(@PathVariable Integer id) {
        TelephonesDTO telephone = telephonesService.getById(id);
        return ResponseEntity.ok(telephone);
    }

    @GetMapping
    public ResponseEntity<List<TelephonesDTO>> getAll() {
        List<TelephonesDTO> telephonesList = telephonesService.getAll();
        return ResponseEntity.ok(telephonesList);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelephoneById(@PathVariable Integer id) {
        telephonesService.deleteTelephoneById(id);
        return ResponseEntity.noContent().build();
    }
}
