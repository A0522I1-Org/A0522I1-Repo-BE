package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Status;
import com.example.spring_pawn_app.service.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class StatusController{

    @Autowired
    StatusService statusService;

    @GetMapping("/status")
    public List<Status> getStatus(){
        return statusService.getAllStatus();
    }
    @GetMapping("/status/{id}")
    public Status findById(@PathVariable("id") int id){
        return statusService.findById(id);
    }
}
