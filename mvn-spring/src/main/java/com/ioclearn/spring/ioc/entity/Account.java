package com.ioclearn.spring.ioc.entity;


import com.ioclearn.spring.ioc.MyIoc.Autowired;
import com.ioclearn.spring.ioc.MyIoc.Component;
import com.ioclearn.spring.ioc.MyIoc.Qualifier;
import com.ioclearn.spring.ioc.MyIoc.Value;

@Component("account")
public class Account {

    @Value("12")
    private Integer id;
    @Value("accountName")
    private String name;

    @Autowired
    @Qualifier("myOrder")
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
