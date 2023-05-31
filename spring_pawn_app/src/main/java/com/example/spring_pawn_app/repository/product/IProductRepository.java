package com.example.spring_pawn_app.repository.product;

import com.example.spring_pawn_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface IProductRepository extends JpaRepository<Product, Integer> {

}
