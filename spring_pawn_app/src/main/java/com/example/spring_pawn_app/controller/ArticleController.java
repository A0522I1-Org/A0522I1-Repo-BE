package com.example.spring_pawn_app.controller;
import com.example.spring_pawn_app.dto.ArticleDTO;
import com.example.spring_pawn_app.model.Article;
import com.example.spring_pawn_app.service.article.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private IArticleService articleService;

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
}
