package com.demo.springsecurity.model;

/*
    用户身份信息
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private String id;
    private String username;
    private String password;
    private String fullName;
    private String mobile;

}
