package com.aoplearn.proxy.cglib;

import com.aoplearn.proxy.RealSubject;
import com.aoplearn.proxy.Subject;
import com.aoplearn.proxy.dynamicProxy.JDKProxySubject;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new DemoMetodIntercetor());
        Subject o = (Subject) enhancer.create();

        o.request();
    }
}
