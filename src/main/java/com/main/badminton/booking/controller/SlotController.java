package com.main.badminton.booking.controller;


import com.main.badminton.booking.dto.request.SlotRequestDTO;
import com.main.badminton.booking.dto.response.SlotResponseDTO;
import com.main.badminton.booking.entity.Slots;
import com.main.badminton.booking.service.interfc.SlotService;
import com.main.badminton.booking.utils.wapper.API;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/slots")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @Operation(
            summary = "Create Slot",
            description = "start time and end time please input format like this Hh:mm:ss(7:30:00)",
            tags = {"Slots"})
    @PostMapping("/create/{yardId}")
    public ResponseEntity<Object> createNewSlot(@PathVariable("yardId") Integer yardId,@RequestBody SlotRequestDTO slotRequestDTO){
        String messages = slotService.createSlot(yardId,slotRequestDTO);
        return ResponseEntity.ok(API.Response.success(messages));
    }
    @Operation(
            summary = "Update Slot",
            description = "start time and end time please input format like this Hh:mm:ss(7:30:00)",
            tags = {"Slots"})
    @PutMapping("/update/{slotId}")
    public ResponseEntity<Object> updateSlot(@PathVariable("slotId") Integer slotId, @RequestBody SlotRequestDTO slotRequestDTO){
        SlotRequestDTO slotResponse = slotService.updateSlot(slotId, slotRequestDTO);
        if(slotResponse != null){
            return ResponseEntity.ok(API.Response.success(slotResponse));
        } else {
            return ResponseEntity.badRequest().body(API.Response.error(HttpStatus.BAD_REQUEST,"Updated unSuccessfully", "error"));
        }
    }

    @GetMapping("{yardId}")
    public API.Response<List<SlotResponseDTO>> getSlotsByYardId(@PathVariable("yardId") Integer yardId,
                                                                @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber){

        return API.Response.success(slotService.getSlotsByYardId(yardId, pageNumber));
    }
}
