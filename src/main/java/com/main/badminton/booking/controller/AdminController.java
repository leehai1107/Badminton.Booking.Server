package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.UserDTO;
import com.main.badminton.booking.service.interfc.IUserSvc;
import com.main.badminton.booking.utils.logger.LogUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private IUserSvc iUserSvc;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/hi")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi admin");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO){
        LogUtil.logInfo(userDTO);
        iUserSvc.update(id, userDTO);
        return ResponseEntity.ok("update success");
    }
}
