package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Status;
import com.example.spring_pawn_app.service.status.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/status")
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*")
public class StatusController {
    @Autowired
    private IStatusService iStatusService;

    /**
     * Created by: PhongTD
     * Date created: 15/05/2023
     * Function: find all status
     *
     * @return List<Status>
     */
    @GetMapping("")
    public List<Status> getListStatus() {
        return iStatusService.findAll();
    }

    /**
     * Created by: PhongTD
     * Date created: 15/05/2023
     * Function: find status by id
     * * @param id
     *
     * @return Status
     */
    @GetMapping("{id}")
    public Status getStatusById(@PathVariable("id") Integer id) {
        return iStatusService.findById(id);
    }

    @GetMapping("/statuses")
    public List<Status> getStatus() {
        return iStatusService.getAllStatus();
    }

    @GetMapping("/statuses/{id}")
    public Status findById(@PathVariable("id") int id) {
        return iStatusService.findById(id);
    }
}
