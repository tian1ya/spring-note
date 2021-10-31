package com.aoplearn.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PkgTypeAspectConfig2 {
    // ProductService 类下只有一个参数，且参数类型是 Long 得方法
    @Pointcut("args(Long) && within(com.aoplearn.service.ProductService)")
//    @Pointcut("args(Long, String) && within(com.aoplearn.service.ProductService)")
    public void matchType() {}

    @Before("matchType()")
    public void before() {
        System.out.println("");
        System.out.println("args。。。");
    }
}
