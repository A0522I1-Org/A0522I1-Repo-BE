package com.example.spring_pawn_app.service.product;

import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository iProductRepository;

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }
}
