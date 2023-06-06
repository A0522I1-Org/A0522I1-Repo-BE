package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.dto.ArticleDTO;
import com.example.spring_pawn_app.model.Article;
import com.example.spring_pawn_app.model.Employee;
import com.example.spring_pawn_app.security.userprincal.UserPrinciple;
import com.example.spring_pawn_app.service.article.ArticleService;
import com.example.spring_pawn_app.service.article.IArticleService;
import com.example.spring_pawn_app.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
public class ArticleController {
    @Autowired
    private IEmployeeService iEmployeeService;
    @Autowired
    private IArticleService articleService;


    @GetMapping("/article-list")
    public ResponseEntity<Page<Article>> getList(@RequestParam(defaultValue = "0") int page) {
        Page<Article> list = articleService.findAllWithPage(PageRequest.of(page, 5));
        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/article-view/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(this.articleService.findArticleById(id), HttpStatus.OK);
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

    /**
     * Created by: TanNC
     * Date created: 12/05/2023
     * Function: create article
     *
     * @param articleDTO
     * @param bindingResult
     * @return HttpStatus.BAD_REQUEST if result is error or HttpStatus.OK if result is not error
     */
    @PostMapping("/articles/save")
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleDTO articleDTO, BindingResult bindingResult, @AuthenticationPrincipal UserPrinciple userPrinciple) {
        new ArticleDTO().validate(articleDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(
                    error -> {
                        String fieldName = error.getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                    });
            return ResponseEntity.badRequest().body(errors);
        }
        Integer idEmployeeCurrent = iEmployeeService.findEmployeeByUserName(userPrinciple.getName()).getId();
        Article article = new Article(null, articleDTO.getTitle(), articleDTO.getImg(), articleDTO.getContent(), LocalDate.now(), articleDTO.isFeature(), false, new Employee(idEmployeeCurrent));
        articleService.save(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/search-article")
    public ResponseEntity<Page<Article>> searchArticleByTitle(@RequestParam String name,
                                                              @RequestParam(defaultValue = "0") int page) {
        Page<Article> articleList = articleService.findArticleByTitle(name, PageRequest.of(page, 5));
        if (articleList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(articleList, HttpStatus.OK);
    }

}
