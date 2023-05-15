package com.example.spring_pawn_app.repository.article;

import com.example.spring_pawn_app.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IArticleRepository extends JpaRepository<Article, Integer>{
    @Query(value = "select a.id, a.content, a.date_public, a.img, a.title,a.employee_id, a.is_feature,a.is_flag,e.name from article a\n" +
            "join employee e on a.employee_id = e.id\n" +
            "where a.is_flag =0", nativeQuery = true  )
    Page<Article> findAllWithPage(Pageable pageable);

    @Query(value = "select article.id , article.content, article.date_public, article.img, article.title,article.is_feature, article.is_flag,article.employee_id,employee.name from article \n" +
            "join employee on article.employee_id = employee.id\n" +
            "where article.is_flag = 0 and article.is_feature = 1",nativeQuery = true  )
    List<Article> findAllWithFeature();

    @Query(value = "select * from article as a \n" +
            "join employee as e on e.id = a.employee_id \n" +
            "where a.id = :id", nativeQuery = true  )
    Optional<Article> findById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update pawnapp.article a set a.is_flag = 1 where a.id = ?;", nativeQuery = true)
    void deleteArticle(int id);

    @Query("select a from Article a where a.isFlag = false and a.isFeature = true")
    List<Article> findArticlesByFlagAndAndFeature();
}
