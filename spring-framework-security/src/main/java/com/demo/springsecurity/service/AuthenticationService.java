package com.demo.springsecurity.service;

import com.demo.springsecurity.model.AuthenticationRequest;
import com.demo.springsecurity.model.UserDto;

public interface AuthenticationService {
    /**
     * 用户认证
     * @param authenticationRequest 用户认证请求 *
     * @return 认证成功的用户信息
     */
    UserDto authentication(AuthenticationRequest authenticationRequest);
}
