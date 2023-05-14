package com.example.spring_pawn_app.service.img;

import com.example.spring_pawn_app.model.Img;
import com.example.spring_pawn_app.repository.img.IImgRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IImgService {
    List<Img> findAll();
}