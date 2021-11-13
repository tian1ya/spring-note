package com.aoplearn.proxy;

public class Proxy implements Subject{
    private Subject realSubject;

    public Proxy(Subject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        System.out.println("before");
        try {
            realSubject.request();
        } catch (Exception e) {
            System.out.println("ex: " + e.getMessage());
        } finally {
            System.out.println("after");
        }
    }
}
