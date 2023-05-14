package com.example.spring_pawn_app.repository.article;

import com.example.spring_pawn_app.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IArticleRepository extends JpaRepository<Article, Integer>{
}
