package com.example.spring_pawn_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private INewsService newsService;
    @GetMapping("/news")
    public ResponseEntity<List<News>> findAllNews(){
        List<News> newsList=newsService.findAll();
        if (newsList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(newsList,HttpStatus.OK);
    }
    @GetMapping("/news/{id}")
    public ResponseEntity<News> findNewsById(@PathVariable Long id){
        Optional<News> news=newsService.findById(id);
        if(!news.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(news.get(),HttpStatus.OK);
    }
    @PostMapping("/news")
    public ResponseEntity<News> createNews(@RequestBody News news){
        newsService.save(news);
        return new ResponseEntity<>(news,HttpStatus.CREATED);
    }
    @DeleteMapping("/news/{id}")
    public ResponseEntity<News> delete(@PathVariable Long id){
        newsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/news/{id}")
    public ResponseEntity<News> update(@PathVariable Long id, @RequestBody News news){
        newsService.update(id,news);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
