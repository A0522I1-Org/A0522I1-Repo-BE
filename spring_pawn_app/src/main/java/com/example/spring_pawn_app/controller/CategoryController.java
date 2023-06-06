package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.service.category.CategoryService;
import com.example.spring_pawn_app.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    ICategoryService iCategoryService;
    @GetMapping("")
    public List<Category> getAllCategory(){
        return iCategoryService.getAllCategory();
    }
    @GetMapping("{id}")
    public Category findById(@PathVariable("id") int id){
        return iCategoryService.findById(id);
    }
}
