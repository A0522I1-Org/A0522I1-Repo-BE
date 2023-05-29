package com.example.spring_pawn_app.service.article;

import com.example.spring_pawn_app.dto.ArticleDTO;
import com.example.spring_pawn_app.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IArticleService {
    Page<Article> findAllWithPage(PageRequest pageable);
    List<Article> findAllWithFeature();
    ArticleDTO findArticleById(int id);
    void deleteArticle(int id);
    void save(Article article);
    Page<Article> findArticleByTitle(String name, PageRequest pageable);
}
