package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.YardRequestDTO;
import com.main.badminton.booking.dto.response.YardResponseDTO;
import com.main.badminton.booking.service.interfc.YardService;
import com.main.badminton.booking.utils.wapper.API;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/yards")
public class YardController {

    @Autowired
    private YardService yardService;

    @PostMapping("/create")
    public ResponseEntity<YardResponseDTO> createYard(@RequestBody YardRequestDTO yardRequestDTO) {
        YardResponseDTO yardResponseDTO = yardService.createYard(yardRequestDTO);
        return ResponseEntity.ok(yardResponseDTO);
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

    @GetMapping()
    public API.Response<List<YardResponseDTO>> getAllYards(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber){
        List<YardResponseDTO> yardResponseDTOS = yardService.getAllYards(pageNumber);
        return API.Response.success(yardResponseDTOS);
    }

    @GetMapping("/active")
    public API.Response<List<YardResponseDTO>> getAllActiveYards(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber){
        List<YardResponseDTO> yardResponseDTOS = yardService.getAllYardsByActiveStatus(pageNumber);
        return API.Response.success(yardResponseDTOS);
    }

    @GetMapping("/search")
    public API.Response<List<YardResponseDTO>> getYardByName(@RequestParam(name = "name") String name,
                                                   @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber){
        List<YardResponseDTO> yardResponseDTOS = yardService.getYardsByName(name, pageNumber);
        return API.Response.success(yardResponseDTOS);
    }

    @GetMapping("/getByHost/{hostId}")
    public List<YardResponseDTO> getAllYardsByHostId(@PathVariable Integer hostId) {
        return yardService.getAllYardsByHostId(hostId);
    }

    @GetMapping("{yardId}/active-slots")
    public YardResponseDTO getYardDetailActiveSlots(@PathVariable Integer yardId) {
        return yardService.getYardDetailActiveSlots(yardId);
    }

    @GetMapping("/getRandom/{numberRandom}")
    public ResponseEntity<Object> getRandomYard(@PathVariable("numberRandom") Integer numberRandom){
        return ResponseEntity.ok(API.Response.success(yardService.getRandomYard(numberRandom)));
    }

    @PostMapping("{yardId}/add-telephones")
    public API.Response<YardResponseDTO> addTelephonesToYard(@PathVariable Integer yardId, @RequestBody List<String> telephones){
        YardResponseDTO yardResponseDTO = yardService.addTelephonesToYard(yardId, telephones);
        if(yardResponseDTO == null) {
            return API.Response.error(HttpStatus.BAD_REQUEST,"Error add telephones to yard", "Bad Request Error");
        }
        return  API.Response.success(yardResponseDTO);
    }

    @PostMapping("{yardId}/add-images")
    public API.Response<YardResponseDTO> addImagesToYard(@PathVariable Integer yardId, @RequestBody List<String> imageUrls){
        YardResponseDTO yardResponseDTO = yardService.addImagesToYard(yardId, imageUrls);
        if(yardResponseDTO == null) {
            return API.Response.error(HttpStatus.BAD_REQUEST,"Error add images to yard", "Bad Request Error");
        }
        return  API.Response.success(yardResponseDTO);
    }
}
