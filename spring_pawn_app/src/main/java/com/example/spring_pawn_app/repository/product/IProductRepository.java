package com.example.spring_pawn_app.repository.product;

import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT product FROM Product product WHERE product.name = ?1 AND product.price = ?2 AND product.category.nameCategory = ?3")
    List<Product> findByProduct(String name, Double price, String categoryName);

    @Query(value = "SELECT product FROM Product product WHERE product.name = ?1 ")
    List<Product> findByProductName(String name);

    @Query(value = "SELECT product FROM Product product WHERE  product.price = ?1")
    List<Product> findByProductPrice(Double price);

    @Query(value = "SELECT product FROM Product product WHERE  product.category.nameCategory = ?1")
    List<Product> findByProductCategoryName(String categoryName );

    @Query(value = "SELECT product FROM Product product WHERE product.name = ?1 AND product.price = ?2")
    List<Product> findByProductNameAndPrice(String name,Double price );

    @Query(value = "SELECT product FROM Product product WHERE product.name = ?1 AND product.category.nameCategory = ?2")
    List<Product> findByProductNameAndCategoryName(String name,String categoryName);

    @Query(value = "SELECT product FROM Product product WHERE  product.price = ?1 AND product.category.nameCategory = ?2")
    List<Product> findByProductPriceAndCategoryName(Double price,String categoryName);

}
