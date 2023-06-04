package com.example.spring_pawn_app.service.product;

import com.example.spring_pawn_app.model.Product;

import java.util.List;

public interface IProductService {
    void save(Product product);
    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    List<Product> findAllByCustomer(Integer id);

}
