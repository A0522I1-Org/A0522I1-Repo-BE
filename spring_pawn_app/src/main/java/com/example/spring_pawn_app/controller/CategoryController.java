package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/categories")
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    /**
     * genarate 13May2023
     * TinPNT
     *
     * @return List of category
     */
    @GetMapping("")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> categoryList = iCategoryService.findAllCategory();
        if (iCategoryService.findAllCategory() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);
    }
     /** Created by: PhongTD
     * Date created: 20/05/2023
     * Function: find all category
     * @return List<Category>
     */
//    @GetMapping("")
//    public List<Category> getAllCategory() {
//        return iCategoryService.findAll();
//    }

    /**
     * Created by: PhongTD
     * Date created: 20/05/2023
     * Function: find category by id
     * * @param id
     * @return Category
     */
    @GetMapping("{id}")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        return iCategoryService.findById(id);
    }
}
