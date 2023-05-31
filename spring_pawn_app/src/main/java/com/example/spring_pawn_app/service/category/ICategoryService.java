package com.example.spring_pawn_app.service.category;

import com.example.spring_pawn_app.model.Category;

import java.util.List;

public interface ICategoryService {


    /**
     * genarate 13May2023
     * TinPNT
     *
     * @return List of category
     */
    List<Category> findAllCategory();

    Category findById(int id);

}
