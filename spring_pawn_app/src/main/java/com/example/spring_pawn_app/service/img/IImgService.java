package com.example.spring_pawn_app.service.img;

import com.example.spring_pawn_app.model.Img;


import java.util.List;

public interface IImgService {
    List<Img> findAll();
    void saveImg(Img img);

}

