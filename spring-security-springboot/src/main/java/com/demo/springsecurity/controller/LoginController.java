package com.demo.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping(value = "/login‐success")
    public String loginSuccess() {
        return getUserName() + " 登录成功";
    }

    @GetMapping(value = "/r/r1")
    @PreAuthorize("hasAuthority('p1')") // 拥有 p1 权限才能访问
//    @PreAuthorize("hasAnyAuthority('p1','p2')") // 拥有 p1 权限才能访问， 如果这里没有注解，那么该方法则不受权限控制
    public String r1() {
        return getUserName() + " 访问资源1";
    }

    @GetMapping(value = "/r/r2")
    @PreAuthorize("hasAuthority('p2')") // 拥有 p1 权限才能访问
    public String r2() {
        return getUserName() + " 访问资源2";
    }

    private String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            return "匿名用户";
        } else
            return ((UserDetails)principal).getUsername();
    }
}
