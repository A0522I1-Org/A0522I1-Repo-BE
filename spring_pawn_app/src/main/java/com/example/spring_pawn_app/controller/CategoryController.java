package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/api")
@RestController
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * genarate 13May2023
     * TinPNT
     *
     * @return List of category
     */
    @GetMapping("/category")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categoryList = iCategoryService.findAllCategory();
        if (iCategoryService.findAllCategory() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);

    }
    @GetMapping("/category/{id}")
    public Category findById(@PathVariable("id") int id){
        return iCategoryService.findById(id);
    }
}
