package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer>{
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c where c.id = :id",
            countQuery ="select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id from contract as c where c.id = :id" ,
            nativeQuery = true)
    Contract findContractById(@Param("id") Integer id);
}
