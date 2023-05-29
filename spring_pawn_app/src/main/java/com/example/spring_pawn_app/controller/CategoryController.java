package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.ContractEditDto;
import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    /**
     * Created by: PhongTD
     * Date created: 20/05/2023
     * Function: find all category
     * @return List<Category>
     */
    @GetMapping("")
    public List<Category> getAllCategory() {
        return iCategoryService.findAll();
    }

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
