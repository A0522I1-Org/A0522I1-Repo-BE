package com.example.spring_pawn_app.service.Finace;

import com.example.spring_pawn_app.dto.Finace.FinaceDTO;
import org.springframework.stereotype.Service;

@Service
public interface IFinaceService {
    FinaceDTO getFinaction();
    Double getAllInterest();

    Double getAllPrice();


    Double getExpectedInterest();
}
