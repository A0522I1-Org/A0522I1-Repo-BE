package com.example.spring_pawn_app.service.role;

import com.example.spring_pawn_app.model.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(String roleName);
}
