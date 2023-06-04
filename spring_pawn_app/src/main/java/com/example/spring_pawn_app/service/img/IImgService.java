package com.example.spring_pawn_app.service.img;

import com.example.spring_pawn_app.model.Img;


import java.util.List;

public interface IImgService {
    /**
     * genarate 13May2023
     * TinPNT
     *
     * @return List of Image
     */
    List<Img> findAll();
    void saveImg(Img img);

}

