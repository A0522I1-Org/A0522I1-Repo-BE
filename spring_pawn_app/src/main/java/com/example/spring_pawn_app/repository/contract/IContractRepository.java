package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.DTO.ContractDTO;
import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer> {

    @Query(value = "  select id ,begin_date,contract_code,end_date,interest,is_flag,customer_id,employee_id,product_id,status_id from contract where status_id =3", nativeQuery = true)
    List<Contract> findAllCurrentInterest();


    @Query(value = "SELECT con.id,con.begin_date,con.employee_id,con.contract_code,con.end_date,con.interest,con.is_flag, p.name, p.price, p.category_id, con.status_id, con.customer_id, cus.customer_name, s.name, cate.name_category, con.product_id " +
            "FROM contract con " +
            "JOIN product p ON p.id = con.product_id " +
            "JOIN category cate ON cate.id = p.category_id " +
            "JOIN status s ON con.status_id = s.id " +
            "JOIN customer cus ON con.customer_id = cus.id " +
            "WHERE con.status_id IN (1, 2) and con.is_flag = 0 AND cus.customer_name LIKE concat('%',?,'%') AND cate.id LIKE concat('%',?,'%')", nativeQuery = true)
    Page<Contract> findAllProductNotPay(String nameCustomer, String categoryId, Pageable page);

    @Query(value = "SELECT con.id,con.begin_date,con.employee_id,con.contract_code,con.end_date,con.interest,con.is_flag, p.name, p.price, p.category_id, con.status_id, con.customer_id, cus.customer_name, s.name, cate.name_category, con.product_id\n" +
            "FROM contract con " +
            "JOIN product p ON p.id = con.product_id \n" +
            "JOIN category cate ON cate.id = p.category_id\n" +
            "JOIN status s ON con.status_id = s.id\n" +
            "JOIN customer cus ON con.customer_id = cus.id\n" +
            "WHERE con.status_id IN (1, 2) and con.is_flag = 0  And con.id = ?;", nativeQuery = true)
    Contract findContractByID(int id);
}
