package com.example.spring_pawn_app.controller;
import com.example.spring_pawn_app.model.Img;
import com.example.spring_pawn_app.service.img.IImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")


public class ImgController {
    @Autowired
    private IImgService imgService;

    /**
     * genarate 13May2023
     *TinPNT
     * @return List of Image
     */
    @GetMapping("img")
    public ResponseEntity<List<Img>> findAllImg() {
        if (imgService.findAll() == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<Img> imgList = imgService.findAll();
        return new ResponseEntity<>(imgList, HttpStatus.OK);
    }
}
