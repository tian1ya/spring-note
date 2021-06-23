package com.demo.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping(value = "/login‐success")
    public String loginSuccess() {
        return " 登录成功";
    }

    @GetMapping(value = "/r/r1")
    public String r1() {
        return " 访问资源1";
    }

    @GetMapping(value = "/r/r2")
    public String r2() {
        return " 访问资源2";
    }

}
