package com.example.spring_pawn_app.service.article;

import com.example.spring_pawn_app.dto.ArticleDTO;
import com.example.spring_pawn_app.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IArticleService {
    Page<Article> findAllWithPage(Pageable pageable);
    List<Article> findAllWithFeature();
    ArticleDTO findById(int id);
    void deleteArticle(int id);
}
