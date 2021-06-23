package com.demo.springsecurity.controller;

import com.demo.springsecurity.model.AuthenticationRequest;
import com.demo.springsecurity.model.UserDto;
import com.demo.springsecurity.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = {"text/plain;charset=UTF‐8"})
    public String login(AuthenticationRequest authenticationRequest, HttpSession session) {
        // 直接在这个方法中塞入参数 HttpSession session， 就可以获取到请求的session
        UserDto userDetails = authenticationService.authentication(authenticationRequest);

        // 存入 session
        session.setAttribute(UserDto.SESSION_USER_KEY, userDetails);

        return userDetails.getFullName() + " 登录成功";
    }

    @GetMapping(value = "/r/r1", produces = {"text/plain;charset=UTF‐8"})
    public String r1(HttpSession session) {
        String fullname = null;
        Object userObj = session.getAttribute(UserDto.SESSION_USER_KEY);
        if (userObj != null) {
            fullname = ((UserDto) userObj).getFullName();
        } else {
            fullname = "匿名";
        }
        return fullname + " 访问资源1";
    }

    @GetMapping(value = "logout", produces = "text/plain;charset=utf‐8")
    public String logout(HttpSession session) {
        session.invalidate();
        return "退出成功";
    }
}
