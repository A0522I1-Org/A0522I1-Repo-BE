package com.example.spring_pawn_app.service.product;

import com.example.spring_pawn_app.model.Product;

import java.util.List;

public interface IProductService {

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */

    List<Product> findByProduct(String name, Double price, String nameCategory);

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    List<Product> findAllByCustomer(Integer id);
}
