package com.example.spring_pawn_app.repository.category;

import com.example.spring_pawn_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer>{
    @Query(value = "select id,name_category from category",nativeQuery = true)
    List<Category> findAllCategory();
}
