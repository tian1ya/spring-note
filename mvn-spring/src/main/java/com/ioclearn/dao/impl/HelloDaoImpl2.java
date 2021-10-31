package com.ioclearn.dao.impl;

import com.ioclearn.dao.HelloDao;

import java.util.Arrays;
import java.util.List;

public class HelloDaoImpl2 implements HelloDao {
    @Override
    public List<String> findAll() {
        return Arrays.asList("21", "22", "23");
    }
}
