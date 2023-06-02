package com.example.spring_pawn_app.repository.category;

import com.example.spring_pawn_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    /**
     * genarate 13May2023
     * TinPNT
     *
     * @return List of category
     */
    @Query(value = "select id,name_category from category", nativeQuery = true)
    List<Category> findAllCategory();


    /**
     * Created by: PhongTD
     * Date created: 12/05/2023
     * Function: find all category
     * @return List<Category>
     */
    @Query("SELECT category FROM Category category")
    List<Category> findAll();

    /**
     * Created by: PhongTD
     * Date created: 12/05/2023
     * Function: find category by id
     * * @param id
     * @return Category
     */
    @Query("SELECT category FROM Category category WHERE category.id = ?1")
    Category findAllById(Integer id);
}
