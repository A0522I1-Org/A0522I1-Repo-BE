package com.example.spring_pawn_app.service.category;

import com.example.spring_pawn_app.model.Category;
import com.example.spring_pawn_app.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Override
    public List<Category> findAllCategory() {
        return iCategoryRepository.findAllCategory();
    }
}
