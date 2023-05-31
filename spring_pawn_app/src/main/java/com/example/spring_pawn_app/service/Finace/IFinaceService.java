package com.example.spring_pawn_app.service.Finace;

import com.example.spring_pawn_app.dto.FinaceDTO;

public interface IFinaceService {
    FinaceDTO getFinaction();
   Double getAllInterest();
   Double getAllPrice();
   Double getExpectedInterest();
}
