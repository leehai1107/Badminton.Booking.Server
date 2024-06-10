package com.main.badminton.booking.controller;

import com.main.badminton.booking.dto.request.UserRequestDTO;
import com.main.badminton.booking.dto.response.UserResponseDTO;
import com.main.badminton.booking.service.impl.UserServiceImpl;
import com.main.badminton.booking.utils.logger.LogUtil;
import com.main.badminton.booking.utils.wapper.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/user")
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

//    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ping")
    public API.Response<?> Ping() {
        String data = "Pong...Pong...Pong...";
        LogUtil.logInfo("Pong...Pong...Pong...");
        return API.Response.success(data);
    }

    @GetMapping("/ping2/{test}")
    public API.Response<?> PingV2(@PathVariable int test) {
        String data = "V2...Pong...Pong...Pong...test:"+test;
        LogUtil.logInfo("Pong...Pong...Pong...");
        return API.Response.success(data);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        if (userResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
        UserResponseDTO userResponseDTO = userService.getUserByUsername(username);
        if (userResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        UserResponseDTO userResponseDTO = userService.getUserByEmail(email);
        if (userResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponseDTO);
    }

    @PostMapping("/search")
    public ResponseEntity<UserResponseDTO> getUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.getUser(userRequestDTO);
        if (userResponseDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userResponseDTO);
    }
}
