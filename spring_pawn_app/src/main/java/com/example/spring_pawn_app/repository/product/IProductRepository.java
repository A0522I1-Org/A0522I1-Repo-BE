package com.example.spring_pawn_app.repository.product;

import com.example.spring_pawn_app.DTO.ContractDTO;
import com.example.spring_pawn_app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

}
