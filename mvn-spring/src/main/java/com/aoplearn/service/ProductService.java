package com.aoplearn.service;

import com.aoplearn.domain.Product;
import com.aoplearn.security.AdminOnly;
import com.aoplearn.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private AuthService authService;

    @AdminOnly
    public void insert(Product product) {
        System.out.println("insert product");
    }

    @AdminOnly
    public void delete(Long id) {
        System.out.println("delete product");
    }
}
