package com.demo.springsecurity.init;

import com.demo.springsecurity.config.ApplicationConfig;
import com.demo.springsecurity.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 加载spring 容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    // 加载 servlet(springMVC) 容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    // url mapping
    @Override
    protected String[] getServletMappings() {
        // 根路径
        return new String[]{"/"};
    }
}
