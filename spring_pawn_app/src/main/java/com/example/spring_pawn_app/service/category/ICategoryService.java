package com.example.spring_pawn_app.service.category;

import com.example.spring_pawn_app.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategory();
    Category findById(int id);
}
