package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.dto.contract.ContractDto;
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
            "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% and p.name like %:nameProduct% and c.begin_date = :beginDate and c.contract_code = :contractCode and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% and p.name like %:nameProduct% and c.begin_date = :beginDate and c.contract_code = :contractCode and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractWithPage(Pageable pageable, @Param("contractCode") String contractCode, @Param("nameCustomer") String nameCustomer, @Param("nameProduct") String nameProduct, @Param("beginDate") String beginDate);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% or '%' and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerWithPage(Pageable pageable,@Param("nameCustomer")String nameCustomer);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.contract_code = :contractCode and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.contract_code = :contractCode and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByContractCodeWithPage(Pageable pageable, @Param("contractCode")String contractCode);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameProductWithPage(Pageable pageable,@Param("nameProduct")String nameProduct);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByBeginDateWithPage(Pageable pageable,@Param("beginDate")String beginDate);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndBeginDate(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("beginDate")String beginDate);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and c.contract_code = :contractCode and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and c.contract_code = :contractCode and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByContractCodeAndBeginDate(Pageable pageable,@Param("contractCode")String contractCode,@Param("beginDate")String beginDate);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and p.name like %:nameProduct% and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and p.name like %:nameProduct% and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameProductAndBeginDate(Pageable pageable,@Param("nameProduct")String nameProduct,@Param("beginDate")String beginDate);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.contract_code = :contractCode and cus.customer_name like %:nameCustomer% and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.contract_code = :contractCode and cus.customer_name like %:nameCustomer% and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndContractCode(Pageable pageable,@Param("contractCode")String contractCode,@Param("nameCustomer")String nameCustomer);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and cus.customer_name like %:nameCustomer% and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and cus.customer_name like %:nameCustomer% and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndNameProduct(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("nameProduct")String nameProduct);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and c.contract_code = :contractCode and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and c.contract_code = :contractCode and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByContractCodeAndNameProduct(Pageable pageable,@Param("contractCode")String contractCode,@Param("nameProduct")String nameProduct);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and c.contract_code = :contractCode and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and c.contract_code = :contractCode and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndBeginDateAndContractCode(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("contractCode")String contractCode,@Param("beginDate")String beginDate);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and p.name like %:nameProduct% and c.contract_code = :contractCode and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and p.name like %:nameProduct% and c.contract_code = :contractCode and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameProductAndBeginDateAndContractCode(Pageable pageable,@Param("nameProduct")String nameProduct,@Param("contractCode")String contractCode,@Param("beginDate")String beginDate);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and p.name like %:nameProduct% and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and p.name like %:nameProduct% and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndBeginDateAndNameProduct(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("nameProduct")String nameProduct,@Param("beginDate")String beginDate);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and cus.customer_name like %:nameCustomer% and c.contract_code = :contractCode and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and cus.customer_name like %:nameCustomer% and c.contract_code = :contractCode and c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndNameProductAndContractCode(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("contractCode")String contractCode,@Param("nameProduct")String nameProduct);

    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.is_flag = 0", nativeQuery = true)
    Page<Contract> findAll(Pageable pageable);
}