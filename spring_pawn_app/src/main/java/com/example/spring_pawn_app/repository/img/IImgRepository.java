package com.example.spring_pawn_app.repository.img;

import com.example.spring_pawn_app.model.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImgRepository extends JpaRepository<Img, Integer> {
}
