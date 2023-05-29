package com.example.spring_pawn_app.service.status;

import com.example.spring_pawn_app.model.Status;

import java.util.List;

public interface IStatusService {
    List<Status> getAllStatus();
    Status findById(int id);
}
