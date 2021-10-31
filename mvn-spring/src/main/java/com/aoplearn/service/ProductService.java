package com.aoplearn.service;

import com.aoplearn.domain.Product;
import com.aoplearn.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private AuthService authService;

    public void insert(Product product) {
        authService.checkAccess();
        System.out.println("insert product");
    }

    public void delete(Long id) {
        authService.checkAccess();
        System.out.println("delete product");
    }
}
