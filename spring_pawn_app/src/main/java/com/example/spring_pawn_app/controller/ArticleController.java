package com.example.spring_pawn_app.controller;
import com.example.spring_pawn_app.dto.article.ArticleDTO;
import com.example.spring_pawn_app.model.Article;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * Created by: TanNC
     * Date created: 12/05/2023
     * Function: create article
     * @param articleDTO
     * @param bindingResult
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus.OK if result is not error
     */
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
        Article article=new Article(null,articleDTO.getTitle(),articleDTO.getImg(),articleDTO.getContent(), LocalDate.now(),articleDTO.isFeature(),false,new Employee(articleDTO.getEmployee()));
        articleService.save(article);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
