package com.example.spring_pawn_app.repository.product;

import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional

public interface IProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Created by: PhongTD
     * Date created: 12/05/2023
     * @param nameEdit
     * @param categoryEdit
     * @param id
     */
    @Query("UPDATE Product SET name = ?1, category = ?2 WHERE id = ?3")
    @Modifying
    void updateFromContractEdit(String nameEdit, Category categoryEdit, Integer id);
}
