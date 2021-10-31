package com.ioclearn.spring.ioc.entity;


import com.ioclearn.spring.ioc.MyIoc.Component;
import com.ioclearn.spring.ioc.MyIoc.Value;

@Component("myOrder")
public class Order {

    @Value("22")
    private Integer id;
    @Value("ordername")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
