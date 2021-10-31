package com.ioclearn.service.impl;

import com.ioclearn.dao.HelloDao;
import com.ioclearn.factory.BeanFactory;
import com.ioclearn.service.HelloService;

import java.util.List;

public class HelloServiceImpl implements HelloService {

    private HelloDao helloDao = (HelloDao) BeanFactory.getDao();

    public HelloServiceImpl() {
        for (int i = 0; i < 10; i++) {
            System.out.println(BeanFactory.getDao());
        }
        /*
            每次返回都是不同得对象。 BeanFactory 使用缓存可以解决
            com.learn.dao.impl.HelloDaoImpl2@49928bd8
            com.learn.dao.impl.HelloDaoImpl2@c900c8e
            com.learn.dao.impl.HelloDaoImpl2@186bc214
            com.learn.dao.impl.HelloDaoImpl2@3dd6e702
            com.learn.dao.impl.HelloDaoImpl2@60c7772b
            com.learn.dao.impl.HelloDaoImpl2@6cbff3c1
            com.learn.dao.impl.HelloDaoImpl2@2a02f720
            com.learn.dao.impl.HelloDaoImpl2@20e6eb95
            com.learn.dao.impl.HelloDaoImpl2@7bc3be8d
            com.learn.dao.impl.HelloDaoImpl2@68f16bcf

            增加缓存之后：
            com.learn.dao.impl.HelloDaoImpl2@4b464941
            com.learn.dao.impl.HelloDaoImpl2@4b464941
            com.learn.dao.impl.HelloDaoImpl2@4b464941
            com.learn.dao.impl.HelloDaoImpl2@4b464941
            com.learn.dao.impl.HelloDaoImpl2@4b464941
            com.learn.dao.impl.HelloDaoImpl2@4b464941
            com.learn.dao.impl.HelloDaoImpl2@4b464941
            com.learn.dao.impl.HelloDaoImpl2@4b464941
            com.learn.dao.impl.HelloDaoImpl2@4b464941
            com.learn.dao.impl.HelloDaoImpl2@4b464941
         */
    }

    @Override
    public List<String> findAll() {
        return helloDao.findAll();
    }
}
