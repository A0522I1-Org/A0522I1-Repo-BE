package com.example.spring_pawn_app.repository.product;

import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select id,name,price,category_id from product where name like %?% and price like %?% and category_id like %?%",nativeQuery=true)
    Page<Product> findByProduct(String name, String price, String categoryId, PageRequest page);
}
