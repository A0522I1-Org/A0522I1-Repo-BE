package com.example.spring_pawn_app.repository.role;

import com.example.spring_pawn_app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
