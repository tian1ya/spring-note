package com.demo.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.demo.springsecurity"
        , includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class WebConfig implements WebMvcConfigurer {
    /*
        这里不再需要这样的拦截器，因为 springsecurity 中已经给我们集成了需要的拦截器，不需要用户自己去定义
     */
//    @Autowired
//    private SimpleAuthenticationInterceptor simpleAuthenticationInterceptor;

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login"); // redirect:/login springSecurity 提供的login 页面
        // / 路径映射到 /WEB-INF/view/login.jsp
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 拦截器在特定路径才会去拦截
//        registry.addInterceptor(simpleAuthenticationInterceptor).addPathPatterns("/r/**");
//    }
}
