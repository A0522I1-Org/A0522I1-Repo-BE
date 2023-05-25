package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;

@Repository
@Transactional
public interface IContractRepository extends JpaRepository<Contract, Integer>{
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c where c.id = :id",
            countQuery ="select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id from contract as c where c.id = :id" ,
            nativeQuery = true)
    Contract findContractById(@Param("id") Integer id);

    @Query(value = "select id,contract_code, customer_id, begin_date, end_date, interest, product_id,employee_id, status_id, is_flag from contract where customer_id = ? and is_flag = 0 ",nativeQuery = true)
    Page<Contract> findContractByCustomerId(PageRequest pageRequest,Integer id);

    @Query(value = "update contract as c set c.status_id = 3 where c.id = :id", countQuery = "update contract as c set c.status_id = 3 where c.id = :id", nativeQuery = true)
    @Modifying
    void updateContractLiquidation(@Param("id") Integer id);

}
