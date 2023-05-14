package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IContractRepository extends JpaRepository<Contract, Integer>{
    @Query(value = "select c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest from contract as c where c.id = :id",
            countQuery ="select c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest from contract as c where c.id = :id" ,
            nativeQuery = true)
    Contract findContractById(String id);
}
