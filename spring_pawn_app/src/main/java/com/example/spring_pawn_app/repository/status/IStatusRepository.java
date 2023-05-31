package com.example.spring_pawn_app.repository.status;

import com.example.spring_pawn_app.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Integer> {
    /**
     * Created by: PhongTD
     * Date created: 12/05/2023
     * @param id
     * @return Status was found by id
     */
    @Query("SELECT status FROM Status status WHERE status.id = ?1")
    Status findAllById(Integer id);

    /**
     * Created by: PhongTD
     * Date created: 12/05/2023
     * @return List status was found by id
     */
    @Query("SELECT status FROM Status status")
    List<Status> findAll();
}
