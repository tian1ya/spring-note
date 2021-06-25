package com.demo.springsecurity.service;

import com.demo.springsecurity.dao.UserDao;
import com.demo.springsecurity.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public SpringUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //登录账号
//        System.out.println("username=" + username); //根据账号去数据库查询... //这里暂时使用静态数据

        UserDto username1 = userDao.getUserByUsername(username);

        if (username1 != null) {
            List<String> permissions = userDao.findPermissionsByUserId(username1.getId());
            String[] pers = new String[permissions.size()];
            permissions.toArray(pers);

            return User
                    .withUsername(username)
                    .password(username1.getPassword())
                    .authorities(pers)
                    .build();
        }
        // 如果用户查不到，返回 null，由provider 来抛出异常
        return null;
    }
}
