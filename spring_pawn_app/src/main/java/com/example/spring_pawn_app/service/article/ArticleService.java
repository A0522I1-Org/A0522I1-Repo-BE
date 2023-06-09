package com.example.spring_pawn_app.service.article;

import com.example.spring_pawn_app.dto.ArticleDTO;
import com.example.spring_pawn_app.model.Article;
import com.example.spring_pawn_app.repository.article.IArticleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticleService {
    @Autowired
    private IArticleRepository articleRepository;

    @Override
    public Page<Article> findAllWithPage(PageRequest pageable) {
        return articleRepository.findAllWithPage(pageable);
    }

    @Override
    public List<Article> findAllWithFeature() {
        return articleRepository.findAllWithFeature();
    }

    @Override
    public ArticleDTO findArticleById(int id) {
        ArticleDTO articleDTO = new ArticleDTO();
        Article article = articleRepository.findArticleById(id).orElse(null);
        BeanUtils.copyProperties(article, articleDTO);
        return articleDTO;
    }

    @Override
    public void deleteArticle(int id) {
        articleRepository.deleteArticle(id);
    }

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Page<Article> findArticleByTitle(String name, PageRequest pageable) {
        return articleRepository.searchArticleByTitle(name, pageable);
    }


}
