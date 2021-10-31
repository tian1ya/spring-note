package com.aoplearn.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AdviceAspecConfig {
    @Pointcut("@annotation(com.aoplearn.security.AdminOnly)")
    public void matchAnno() {
    }

//    @Pointcut("execution(**..find*(Long))")
//    public void matchLongArgs() {}

    @Pointcut("@annotation(com.aoplearn.security.AdminOnly) throws java.lang.RuntimeException)")
    public void matchException() {
    }

    @Pointcut("execution(String com.aoplearn.service.ProductService.*(..))")
    public void matchReturn() {
    }

    /**************** Advice ********/

//    @After("matchAnno()")
//    public void after() {
//        System.out.println("#### After");
//    }
//
//    @After("matchException()")
//    public void afterThrow() {
//        System.out.println("### Exception");
//    }
//
//    @AfterReturning(value = "matchReturn()", returning = "result")
//    public void afterReturn(Object result) {
//        System.out.println("### result" + result);
//    }

    /*
        环绕，joinPoint.proceed(joinPoint.getArgs()); 这个就是指向要切得那个方法
        在这个方法前，后，抛出异常，返回等地方均可以进行一些操作
     */
    @Around("matchAnno()")
    public Object after(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            System.out.println("Before****");
            result = joinPoint.proceed(joinPoint.getArgs());
            System.out.println("AfterReturning");
            return result;
        } catch (Throwable throwable) {
            System.out.println("throwable &&& ");
            throwable.printStackTrace();
        } finally {
            System.out.println("finally....");
        }
        return result;
    }
}
