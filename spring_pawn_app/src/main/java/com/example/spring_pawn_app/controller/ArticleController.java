package com.example.spring_pawn_app.controller;
import com.example.spring_pawn_app.dto.ArticleDTO;
import com.example.spring_pawn_app.model.Article;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.service.article.ArticleService;
import com.example.spring_pawn_app.service.article.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    
//    @Autowired
//    private Employee employee;

    @GetMapping("/list")
    public ResponseEntity<Page<Article>> getList(@PageableDefault(size = 5) Pageable pageable){
        Page<Article> list = articleService.findAllWithPage(pageable);
        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/list/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id){
        return new ResponseEntity<>(this.articleService.findById(id), HttpStatus.OK);
    }
    
    @PatchMapping("/delete-article/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/feature")
    public ResponseEntity<List<Article>> getList() {
        List<Article> list = articleService.findAllWithFeature();
        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
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
