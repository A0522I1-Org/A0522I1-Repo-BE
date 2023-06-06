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
    @Query(value = "select a.id, a.content, a.public_date, a.img, a.title,a.employee_id, a.is_feature,a.is_flag,e.name from article a\n" +
            "join employee e on a.employee_id = e.id\n" +
            "where a.is_flag =0 order by a.public_date DESC", nativeQuery = true  )
    Page<Article> findAllWithPage(Pageable pageable);

    @Query(value = "select a.id, a.content, a.public_date, a.img, a.title,a.employee_id, a.is_feature,a.is_flag,e.name from article a\n" +
            "join employee e on a.employee_id = e.id\n" +
            "where a.is_flag = 0 and a.is_feature = 1",nativeQuery = true  )
    List<Article> findAllWithFeature();

    @Query(value = "select a.id, a.content, a.public_date, a.img, a.title,a.employee_id, a.is_feature,a.is_flag,e.name from article a\n" +
            "join employee as e on e.id = a.employee_id \n" +
            "where a.id = :id", nativeQuery = true  )
    Optional<Article> findArticleById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "update pawnapp.article a set a.is_flag = 1 where a.id = ?;", nativeQuery = true)
    void deleteArticle(int id);

    @Query(value = "select a.id, a.content, a.public_date, a.img, a.title,a.employee_id, a.is_feature,a.is_flag,e.name from article a\n" +
                    "join employee e on a.employee_id = e.id\n" +
                    "where a.is_flag =0 and a.title like %?1% order by a.public_date DESC", nativeQuery = true)
    Page<Article> searchArticleByTitle(String name, Pageable pageable);

}
