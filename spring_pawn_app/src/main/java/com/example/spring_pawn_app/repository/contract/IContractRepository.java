package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.model.Status;
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
//    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c where c.id = :id",
//            countQuery ="select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id from contract as c where c.id = :id" ,
//            nativeQuery = true)
//    Contract findContractById(@Param("id") Integer id);
    /**
     * Created by: NamHV
     * Date create 3/06/2023
     * Function : get contract customer
//     * @param id
//     * @return contract
     */
    @Query("select  contract FROM Contract contract WHERE contract.customer.id = ?1  AND contract.isFlag = false AND contract.status.id = 1 ")
    List<Contract> findContractByCustomerId(Integer id);



    /**
     * Created by: NamHV
     * Date create: 3/06/2023
     * Function: update contract
//     * @param id
     */
//    @Query(value = "update contract as c set c.status_id = 2 where c.id = :id", countQuery = "update contract as c set c.status_id = 2 where c.id = :id and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
//    @Modifying
//    void updateContractLiquidation(@Param("id") Integer id);

}
