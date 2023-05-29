package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/category")
    public List<Category> getAllCategory(){
        return categoryService.getAllCategory();
    }
    @GetMapping("/category/{id}")
    public Category findById(@PathVariable("id") int id){
        return categoryService.findById(id);
    }
}
