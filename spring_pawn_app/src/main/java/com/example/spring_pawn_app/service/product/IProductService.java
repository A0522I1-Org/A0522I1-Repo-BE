package com.example.spring_pawn_app.service.product;

import com.example.spring_pawn_app.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> findByProduct(String name, Double price, String nameCategory);
    List<Product> findAllByCustomer(Integer id);
}
