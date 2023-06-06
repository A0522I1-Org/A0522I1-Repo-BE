package com.example.spring_pawn_app.service.article;

import com.example.spring_pawn_app.model.Article;
import com.example.spring_pawn_app.repository.article.IArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ArticleService implements IArticleService{
    @Autowired
    private IArticleRepository iArticleRepository;
    @Override
    public void save(Article article) {
        iArticleRepository.save(article);
    }

}
