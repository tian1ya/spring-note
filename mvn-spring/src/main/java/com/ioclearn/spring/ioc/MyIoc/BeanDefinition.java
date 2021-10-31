package com.ioclearn.spring.ioc.MyIoc;

public class BeanDefinition {
    public BeanDefinition(String beanName, Class beanClz) {
        this.beanName = beanName;
        this.beanClz = beanClz;
    }

    private String beanName;
    private Class beanClz;

    public Class getBeanClass() {
        return beanClz;
    }

    public String getBeanName() {
        return beanName;
    }
}
