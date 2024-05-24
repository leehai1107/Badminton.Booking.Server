package com.main.badminton.booking.controller;

import com.main.badminton.booking.utils.logger.LogUtil;
import com.main.badminton.booking.utils.wapper.API;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/user")
@RestController
public class UserController {
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
}
