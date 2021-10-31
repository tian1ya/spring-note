package com.ioclearn;

import com.ioclearn.spring.ioc.MyIoc.MySpringIoc;
import com.ioclearn.spring.ioc.entity.Account;
import com.ioclearn.spring.ioc.entity.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.learn.spring.ioc");
//        for (String name : applicationContext.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }

        MySpringIoc springIoc = new MySpringIoc("com.learn.spring.ioc.entity");
        for (String beanDefinitionName : springIoc.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("========================================");
        Account account = (Account)springIoc.getBean("account");
        System.out.println(account.getName());
        System.out.println(account.getId());

        Order order = account.getOrder();
        System.out.println(order.getId());
        System.out.println(order.getName());
        System.out.println("====================================");
    }
}
