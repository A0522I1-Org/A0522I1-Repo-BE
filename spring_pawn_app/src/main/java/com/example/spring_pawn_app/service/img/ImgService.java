package com.example.spring_pawn_app.service.img;

import com.example.spring_pawn_app.model.Img;
import com.example.spring_pawn_app.repository.img.IImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImgService implements IImgService {
    @Autowired
    private IImgRepository iImgRepository;


    @Override
    /**
     * genarate 13May2023
     *TinPNT
     * @return List of Image
     */
    public List<Img> findAll() {
        return iImgRepository.findAllImg();
    }

    @Override
    public void saveImg(Img img) {
        iImgRepository.save(img);

    }
}