package com.main.badminton.booking.controller;

import java.util.List;

import com.main.badminton.booking.dto.request.YardImagesDTO;
import com.main.badminton.booking.dto.response.YardImageDTO;
import com.main.badminton.booking.service.interfc.YardImageService;
import com.main.badminton.booking.utils.wapper.API;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/yardImages")
@RequiredArgsConstructor
public class YardImagesController {

    @Autowired
    private YardImageService yardImageService;

    @GetMapping("/get-images/{yardId}")
    public ResponseEntity<Object> getImagesByYardId(@PathVariable("yardId") Integer yardId){
        List<YardImageDTO> list = yardImageService.findAllByYard_Id(yardId);
        return ResponseEntity.ok(API.Response.success(list));
    }

    @PostMapping("/add-new-image")
    public API.Response addNewImage(@RequestBody YardImagesDTO yardImagesDTO){
        yardImageService.createYardImage(yardImagesDTO);

        return API.Response.success("Add new image success");
    }
}
