package com.aoplearn.proxy.dynamicProxy;

import com.aoplearn.proxy.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxySubject implements InvocationHandler {
    private RealSubject realSubject;

    public JDKProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = null;
        try {
            result = method.invoke(realSubject, args);
        } catch (Exception e) {
            System.out.println("ex: " + e.getMessage());
        } finally {
            System.out.println("after");
        }

        return result;
    }
}
