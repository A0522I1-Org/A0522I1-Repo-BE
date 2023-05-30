package com.example.spring_pawn_app.service.status;

import com.example.spring_pawn_app.model.Status;
import com.example.spring_pawn_app.repository.status.IStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements IStatusService{
    @Autowired
    private IStatusRepository iStatusRepository;

    /**
     * Created by: PhongTD
     * Date created: 12/05/2023
     * @param id
     * @return Status was found by id
     */
    public Status findById(Integer id) {
        return iStatusRepository.findAllById(id);
    }

    /**
     * Created by: PhongTD
     * Date created: 12/05/2023
     * @return List status was found by id
     */
    @Override
    public List<Status> findAll() {
        return iStatusRepository.findAll();
    }

    @Override
    public List<Status> getAllStatus() {
        return iStatusRepository.findAll();
    }

    @Override
    public Status findById(int id) {
        return iStatusRepository.findById(id).get();
    }
}
