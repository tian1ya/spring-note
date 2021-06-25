package com.demo.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
        配置用户信息服务
        UserDetailsService: 用于查询获取用户信息
        暂时使用InMemoryUserDetailsManager实现类，并在其中分别创建了zhangsan、lisi两个用 户，并设置密码和权限。
     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1","p2").build());
//        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
//        return manager;
//    }

    /*
        密码编码器，对比服务器密码和用户输入的密码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
        配置安全拦截机制
        "/r/**": uri 需要经过认证才能访问
        其他 url 放行
        支持form 表单登陆，登陆成功后跳转到URI /login‐success
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/r/r1").hasAuthority("p1") // /r/r1 必须有 p1 的权限, 在方法中使用了基于方法的权限 @PreAuthorize 所以注释调这里基于 url 的权限
//                .antMatchers("/r/r2").hasAuthority("p2") // /r/r1 必须有 p1 的权限
                .antMatchers("/r/**")
                .authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin() // 支持form 表单登陆
                .loginPage("/login-view") // 登陆页面
                .loginProcessingUrl("/login") // 指定登录处理的URL，也就是用户名、密码表单提交的目的路径
                .successForwardUrl("/login‐success") // 登陆成功后跳转到URI /login‐success
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

    }
}
