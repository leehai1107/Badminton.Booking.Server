package com.main.badminton.booking.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

    @GetMapping("/login/oauth2/code/google")
    public String login(OAuth2AuthenticationToken authentication) {
        // Xử lý logic sau khi người dùng đăng nhập thành công
        // Ví dụ: lấy thông tin user từ authentication
        String name = authentication.getName(); // username
        System.out.println(name);
        // có thể trả về trang HTML để hiển thị thông tin hoặc thực hiện các thao tác lưu trữ khác
        return "Logged in as: " + name;
    }
}
