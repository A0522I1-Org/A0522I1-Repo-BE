package com.example.spring_pawn_app.controller;
import com.example.spring_pawn_app.dto.ArticleDTO;
import com.example.spring_pawn_app.model.Article;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/article/save")
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleDTO articleDTO, BindingResult bindingResult){
        new ArticleDTO().validate(articleDTO,bindingResult);
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(
                    error -> {
                        String fieldName =  error.getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                    });
            return  ResponseEntity.badRequest().body(errors);
        }
        Article article=new Article(null,articleDTO.getTitle(),articleDTO.getImg(),articleDTO.getContent(), LocalDate.now(),articleDTO.isFeature(),false,new Employee(articleDTO.getEmployee().getId()));
        articleService.save(article);
        return ResponseEntity.ok("Thêm tin tức thành công");
    }
}
