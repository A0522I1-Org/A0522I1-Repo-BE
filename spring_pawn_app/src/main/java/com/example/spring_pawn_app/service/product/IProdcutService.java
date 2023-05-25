package com.example.spring_pawn_app.service.product;

import com.example.spring_pawn_app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProdcutService {
    Page<Product> findByProduct(String name, String price, String categoryId, PageRequest page);
}
