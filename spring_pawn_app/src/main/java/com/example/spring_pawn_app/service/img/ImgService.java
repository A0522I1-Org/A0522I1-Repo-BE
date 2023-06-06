package com.example.spring_pawn_app.service.img;

import com.example.spring_pawn_app.model.Img;
import com.example.spring_pawn_app.repository.img.IImgRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class ImgService implements IImgService{
    @Autowired
    IImgRepository iImgRepository;
    @Override
    public void saveImg(Img img) {
        iImgRepository.save(img);
    }
}
