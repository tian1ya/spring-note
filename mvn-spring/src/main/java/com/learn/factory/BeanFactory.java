package com.learn.factory;

import com.learn.dao.HelloDao;
import com.learn.dao.impl.HelloDaoImpl;
import com.learn.dao.impl.HelloDaoImpl2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
    private static Properties properties;
    private static Map<String, Object> objCache = new HashMap<>();

    static {
        properties = new Properties();
        try {
            properties.load(BeanFactory.class.getClassLoader().getResourceAsStream("factory.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object getDao() {
        String clzName = properties.getProperty("helloDap");
        return objCache.containsKey(clzName) ? objCache.get(clzName) : getDao(clzName);
    }

    private static Object getDao(String beanName) {
        /*
            业务发送变化，代码就需要在这里变化
         */
        // 第一种方式： 强依赖，需要修改代码
//        return new HelloDaoImpl();
//        return new HelloDaoImpl2();
        // 第二种方式，弱依赖，修改配置文件，代码不动
        // 这里将创建对象得方式交给了 BeanFactory， 这种将控制权交给别人得方式就是Ioc
        try {
            Class<?> aClass = Class.forName(beanName);
            Object instance = aClass.getConstructor().newInstance();
            objCache.put(beanName, instance);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
