package com.example.spring_pawn_app.service.status;

import com.example.spring_pawn_app.model.Status;
import com.example.spring_pawn_app.repository.status.IStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements IStatusService{

    @Autowired
    IStatusRepository iStatusRepository;

    @Override
    public List<Status> getAllStatus() {
        return iStatusRepository.findAll();
    }

    @Override
    public Status findById(int id) {
        return iStatusRepository.findById(id).get();
    }
}
