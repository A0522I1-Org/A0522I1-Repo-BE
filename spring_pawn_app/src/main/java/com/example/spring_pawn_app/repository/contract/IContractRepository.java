package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.DTO.ContractDTO;
import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer>{
    @Query(value = "SELECT p.price, c.id, p.id \n" +
            "FROM contract c \n" +
            "JOIN product p ON c.id = p.id \n" +
            "WHERE c.status_id IN (1, 2);",nativeQuery = true)
    List<Contract> findALlPriceByContract();


    @Query(value = "SELECT con.id,con.begin_date,con.employee_id,con.end_date,con.interest,con.is_flag, p.name, p.price, p.category_id, con.status_id, con.customer_id, cus.name, s.name, cate.name_category, con.product_id\n" +
            "FROM contract con\n" +
            "JOIN product p ON p.id = con.product_id \n" +
            "JOIN category cate ON cate.id = p.category_id\n" +
            "JOIN status s ON con.status_id = s.id\n" +
            "JOIN customer cus ON con.customer_id = cus.id\n" +
            "WHERE con.status_id IN (1, 2) AND cus.name LIKE %?% AND cate.id LIKE %?%;",nativeQuery = true)
    Page<Contract> findAllProductNotPay( String nameCustomer, String categoryId,PageRequest page);

    @Query(value = "SELECT con.id,con.begin_date,con.employee_id,con.end_date,con.interest,con.is_flag, p.name, p.price, p.category_id, con.status_id, con.customer_id, cus.name, s.name, cate.name_category, con.product_id\n" +
            "FROM contract con\n" +
            "JOIN product p ON p.id = con.product_id \n" +
            "JOIN category cate ON cate.id = p.category_id\n" +
            "JOIN status s ON con.status_id = s.id\n" +
            "JOIN customer cus ON con.customer_id = cus.id\n" +
            "WHERE con.status_id IN (1, 2) And con.id = ?;",nativeQuery = true)
    Contract findContractByID(int id);
}
