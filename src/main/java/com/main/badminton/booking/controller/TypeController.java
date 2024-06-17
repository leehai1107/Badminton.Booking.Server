package com.main.badminton.booking.controller;


import com.main.badminton.booking.dto.request.TypeRequestDTO;
import com.main.badminton.booking.dto.response.TypeResponseDTO;
import com.main.badminton.booking.service.interfc.TypeService;
import com.main.badminton.booking.utils.wapper.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/types")
public class TypeController {
    @Autowired
    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping()
    public API.Response<List<TypeResponseDTO>> getAllTypes(){
        List<TypeResponseDTO> types = typeService.getAllTypes();
        return API.Response.success(types);
    }

    @PostMapping("/create")
    public API.Response<String> createType(@RequestBody TypeRequestDTO typeRequestDTO){
        try {
            typeService.createType(typeRequestDTO);
            return API.Response.success("Type Created Successfully");
        }catch (Exception e){
            return API.Response.error(HttpStatus.BAD_REQUEST, "Type Created Unsuccessfully", e.getMessage());
        }
    }

    @PutMapping("/update/{typeId}")
    public API.Response<TypeResponseDTO> updateType(@PathVariable Integer typeId, @RequestBody TypeRequestDTO typeRequestDTO){
        try {
            TypeResponseDTO typeResponseDTO = typeService.updateType(typeId, typeRequestDTO);
            return API.Response.success(typeResponseDTO);
        }catch (Exception e){
            return API.Response.error(HttpStatus.BAD_REQUEST, "Type Created Unsuccessfully", e.getMessage());
        }
    }
}
