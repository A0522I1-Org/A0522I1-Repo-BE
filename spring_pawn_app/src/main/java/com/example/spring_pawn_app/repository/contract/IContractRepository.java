package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.dto.contract.ContractProjection;
import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
@Transactional
public interface IContractRepository extends JpaRepository<Contract, Integer> {
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c where c.id = :id",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id from contract as c where c.id = :id and c.is_flag = 0",
            nativeQuery = true)
    Contract findContractById(@Param("id") Integer id);

    @Query(value = "update contract as c set c.status_id = 3 where c.id = :id", countQuery = "update contract as c set c.status_id = 3 where c.id = :id", nativeQuery = true)
    @Modifying
    void updateContractPayment(@Param("id") Integer id);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% or p.name like %:nameProduct% or c.begin_date like %:beginDate% or c.contract_code like %:contractCode% and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% or p.name like %:nameProduct% or c.begin_date like %:beginDate% or c.contract_code like %:contractCode% and c.is_flag = 0", nativeQuery = true)
    Page<ContractProjection> findAllContractWithPage(Pageable pageable, @Param("contractCode") String contractCode, @Param("nameCustomer") String nameCustomer, @Param("nameProduct") String nameProduct, @Param("beginDate") String beginDate);


}