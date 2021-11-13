package com.aoplearn.proxy;

/*
    静态代理，代理类和客户端有共同得接口
 */
public class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("real subject execute");
    }
}
