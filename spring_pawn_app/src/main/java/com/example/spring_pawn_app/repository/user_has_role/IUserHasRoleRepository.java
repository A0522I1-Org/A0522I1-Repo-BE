package com.example.spring_pawn_app.repository.user_has_role;

import com.example.spring_pawn_app.model.UserHasRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserHasRoleRepository extends JpaRepository<UserHasRole, Integer> {
}
