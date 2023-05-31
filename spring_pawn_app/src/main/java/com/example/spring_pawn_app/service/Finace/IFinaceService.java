package com.example.spring_pawn_app.service.Finace;

import com.example.spring_pawn_app.DTO.FinaceDTO;
import org.springframework.stereotype.Service;

@Service
public interface IFinaceService {
    /**
     * genarate 13May2023
     * TinPNT
     *
     * @return Finance of pawnshop
     */
    FinaceDTO getFinaction();

    /**
     * genarate 13May
     * TinPNT
     *
     * @return current Interest of pawn shop
     */
    Double getAllInterest();

    /**
     * genarate 13May
     * TinPNT
     *
     * @return investment of pawn shop
     */
    Double getAllPrice();

    /**
     * genarate 13May
     * TinPNT
     *
     * @return expected interest of pawn shop
     */
    Double getExpectedInterest();
}
