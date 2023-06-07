package com.example.spring_pawn_app.service.category;

import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    /**
     * genarate 13May2023
     * TinPNT
     *
     * @return List of category
     */
    @Override
    public List<Category> findAllCategory() {
        return iCategoryRepository.findAllCategory();
    }

//    /**
//     * Created by: PhongTD
//     * Date created: 12/05/2023
//     * Function: find all category
//     * @return List<Category>
//     */
//    @Override
//    public List<Category> findAll() {
//        return iCategoryRepository.findAll();
//    }

    /**
     * Created by: PhongTD
     * Date created: 12/05/2023
     * Function: find category by id
     * * @param id
     * @return Category
     */
    @Override
    public Category findById(Integer id) {
        return iCategoryRepository.findAllById(id);
    }
}
