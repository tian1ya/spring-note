package com.demo.springsecurity.model;

/*
    用户身份信息
 */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {

    public static final String SESSION_USER_KEY = "_user";

    private String id;
    private String username;
    private String password;
    private String fullName;
    private String mobile;

    /*
        用户权限
     */
    private Set<String> authorities;

}
