package com.example.spring_pawn_app.repository.img;

import com.example.spring_pawn_app.model.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImgRepository extends JpaRepository<Img, Integer> {
    @Query(value = "select id,img,product_id from img",nativeQuery = true)
    List<Img> findAllImg();

}
