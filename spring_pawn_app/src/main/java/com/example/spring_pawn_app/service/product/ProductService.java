package com.example.spring_pawn_app.service.product;

import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProdcutService{
    @Autowired
    IProductRepository iProductRepository;
    @Override
    public Page<Product> findByProduct(String name, String price, String categoryId, PageRequest page) {
        return iProductRepository.findByProduct( name,price,categoryId,page );
    }



}
