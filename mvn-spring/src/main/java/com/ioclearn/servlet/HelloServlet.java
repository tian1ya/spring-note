package com.ioclearn.servlet;

import com.ioclearn.service.HelloService;
import com.ioclearn.service.impl.HelloServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    protected HelloService helloService = new HelloServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> list = helloService.findAll();
        resp.getWriter().write(list.toString());
    }
}
