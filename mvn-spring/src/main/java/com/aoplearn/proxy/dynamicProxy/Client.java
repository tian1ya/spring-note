package com.aoplearn.proxy.dynamicProxy;

import com.aoplearn.proxy.RealSubject;
import com.aoplearn.proxy.Subject;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Subject instance = (Subject)Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{Subject.class}, new JDKProxySubject(new RealSubject()));
        instance.request();
    }
}
