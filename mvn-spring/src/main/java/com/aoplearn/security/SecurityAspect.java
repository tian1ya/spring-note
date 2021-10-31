package com.aoplearn.security;

import com.ioclearn.spring.ioc.MyIoc.Component;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component
public class SecurityAspect {

    @Autowired
    private AuthService authService;
    // 定义切入点，基于注解得拦截
    @Pointcut("@annotation(AdminOnly)")
    public void adminOnly() {

    }
    // 切入点之前拦截
    @Before("adminOnly()")
    public void check() {
        authService.checkAccess();
    }
}
