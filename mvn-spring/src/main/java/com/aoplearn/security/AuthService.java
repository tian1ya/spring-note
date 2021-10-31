package com.aoplearn.security;

import com.ioclearn.spring.ioc.MyIoc.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public void checkAccess () {
        String user = CurrentUserHolder.get();
        if (!"admin".equals(user)) {
            throw new RuntimeException("role is not admin, operation not allowed");
        }
    }
}
