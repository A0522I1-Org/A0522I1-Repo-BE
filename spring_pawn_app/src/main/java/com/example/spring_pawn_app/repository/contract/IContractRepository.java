package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;

@Repository
@Transactional
public interface IContractRepository extends JpaRepository<Contract, Integer> {

    /**
     * Created by: HoangVV
     * Date create: 15/05/2023
     * Function : get contract with id
     * @param id
     * @return contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c where c.id = :id and c.is_flag = 0",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id from contract as c where c.id = :id and c.is_flag = 0",
            nativeQuery = true)
    Contract findContractById(@Param("id") Integer id);

    /**
     * Created by: HoangVV
     * Date create: 15/05/2023
     * Function: update contract with id
     * @param id
     */
    @Query(value = "update contract as c set c.status_id = 2 where c.id = :id", countQuery = "update contract as c set c.status_id = 2 where c.id = :id", nativeQuery = true)
    @Modifying
    void updateContractPayment(@Param("id") Integer id);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function : get all contract with pageable,contractCode,nameCustomer,nameProduct,beginDate
     * @param pageable
     * @param contractCode
     * @param nameCustomer
     * @param nameProduct
     * @param beginDate
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% and p.name like %:nameProduct% and c.begin_date = :beginDate and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% and p.name like %:nameProduct% and c.begin_date = :beginDate and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractWithPage(Pageable pageable, @Param("contractCode") String contractCode, @Param("nameCustomer") String nameCustomer, @Param("nameProduct") String nameProduct, @Param("beginDate") String beginDate);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,nameCustomer
     * @param pageable
     * @param nameCustomer
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% or '%' and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where cus.customer_name like %:nameCustomer% and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerWithPage(Pageable pageable,@Param("nameCustomer")String nameCustomer);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,contractCode
     * @param pageable
     * @param contractCode
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByContractCodeWithPage(Pageable pageable, @Param("contractCode")String contractCode);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,nameProduct
     * @param pageable
     * @param nameProduct
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameProductWithPage(Pageable pageable,@Param("nameProduct")String nameProduct);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,beginDate
     * @param pageable
     * @param beginDate
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByBeginDateWithPage(Pageable pageable,@Param("beginDate")String beginDate);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,nameCustomer,beginDate
     * @param pageable
     * @param nameCustomer
     * @param beginDate
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndBeginDate(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("beginDate")String beginDate);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,contractCode,beginDate
     * @param pageable
     * @param contractCode
     * @param beginDate
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByContractCodeAndBeginDate(Pageable pageable,@Param("contractCode")String contractCode,@Param("beginDate")String beginDate);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,nameProduct,beginDate
     * @param pageable
     * @param nameProduct
     * @param beginDate
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and p.name like %:nameProduct% and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and p.name like %:nameProduct% and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameProductAndBeginDate(Pageable pageable,@Param("nameProduct")String nameProduct,@Param("beginDate")String beginDate);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,contractCode,nameCustomer
     * @param pageable
     * @param contractCode
     * @param nameCustomer
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.contract_code = :contractCode and cus.customer_name like %:nameCustomer% and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.contract_code = :contractCode and cus.customer_name like %:nameCustomer% and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndContractCode(Pageable pageable,@Param("contractCode")String contractCode,@Param("nameCustomer")String nameCustomer);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,nameProduct,nameCustomer
     * @param pageable
     * @param nameCustomer
     * @param nameProduct
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and cus.customer_name like %:nameCustomer% and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and cus.customer_name like %:nameCustomer% and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndNameProduct(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("nameProduct")String nameProduct);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,contractCode,nameProduct
     * @param pageable
     * @param contractCode
     * @param nameProduct
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByContractCodeAndNameProduct(Pageable pageable,@Param("contractCode")String contractCode,@Param("nameProduct")String nameProduct);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,contractCode,nameCustomer,beginDate
     * @param pageable
     * @param nameCustomer
     * @param contractCode
     * @param beginDate
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndBeginDateAndContractCode(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("contractCode")String contractCode,@Param("beginDate")String beginDate);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,contractCode,nameProduct,beginDate
     * @param pageable
     * @param nameProduct
     * @param contractCode
     * @param beginDate
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and p.name like %:nameProduct% and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and p.name like %:nameProduct% and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameProductAndBeginDateAndContractCode(Pageable pageable,@Param("nameProduct")String nameProduct,@Param("contractCode")String contractCode,@Param("beginDate")String beginDate);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,nameCustomer,nameProduct,beginDate
     * @param pageable
     * @param nameCustomer
     * @param nameProduct
     * @param beginDate
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and p.name like %:nameProduct% and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.begin_date = :beginDate and cus.customer_name like %:nameCustomer% and p.name like %:nameProduct% and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndBeginDateAndNameProduct(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("nameProduct")String nameProduct,@Param("beginDate")String beginDate);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract with pageable,contractCode,nameCustomer,nameProduct
     * @param pageable
     * @param nameCustomer
     * @param contractCode
     * @param nameProduct
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and cus.customer_name like %:nameCustomer% and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where p.name like %:nameProduct% and cus.customer_name like %:nameCustomer% and c.contract_code = :contractCode and c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContractByNameCustomerAndNameProductAndContractCode(Pageable pageable,@Param("nameCustomer")String nameCustomer,@Param("contractCode")String contractCode,@Param("nameProduct")String nameProduct);

    /**
     * Created by: HoangVV
     * Date create: 20/05/2023
     * Function: get all contract
     * @param pageable the pageable to request a paged result, can be {@link Pageable#unpaged()}, must not be
     *          {@literal null}.
     * @return page contract
     */
    @Query(value = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
            "inner join customer as cus on cus.id = c.customer_id where c.is_flag = 0 and c.status_id = 1",
            countQuery = "select c.id,c.contract_code, c.customer_id, c.begin_date, c.end_date, c.interest, c.product_id, c.employee_id, c.status_id, c.is_flag from contract as c inner join product as p on p.id = c.product_id " +
                    "inner join customer as cus on cus.id = c.customer_id where c.is_flag = 0 and c.status_id = 1", nativeQuery = true)
    Page<Contract> findAllContract(Pageable pageable);

    @Query(value = "  select id ,begin_date,contract_code,end_date , interest,is_flag,customer_id,employee_id,product_id,status_id from contract where status_id =2", nativeQuery = true)
    List<Contract> findAllCurrentInterest();

    /**
     * genarate 13May2023
     * TinPNT
     * @return List of contract with status in (1) and can find with customer name and category id of product
     */
    @Query(value = "SELECT con.id,con.begin_date,con.employee_id,con.contract_code,con.end_date,con.interest,con.is_flag, p.name, p.price, p.category_id, con.status_id, con.customer_id, cus.customer_name, s.name, cate.name_category, con.product_id " +
            "FROM contract con " +
            "JOIN product p ON p.id = con.product_id " +
            "JOIN category cate ON cate.id = p.category_id " +
            "JOIN status s ON con.status_id = s.id " +
            "JOIN customer cus ON con.customer_id = cus.id " +
            "WHERE con.status_id in (1) and con.is_flag = 0 AND cus.customer_name LIKE concat('%',?,'%') AND cate.name_category LIKE concat('%',?,'%') ", nativeQuery = true)
    Page<Contract> findAllProductNotPay(String nameCustomer, String categoryName, Pageable page);

    /**
     * genarate 13May2023
     * TinPNT
     * @return contract with status in (1,2) by ID of contract
     */
    @Query(value = "SELECT con.id,con.begin_date,con.employee_id,con.contract_code,con.end_date,con.interest,con.is_flag, p.name, p.price, p.category_id, con.status_id, con.customer_id, cus.customer_name, s.name, cate.name_category, con.product_id\n" +
            "FROM contract con " +
            "JOIN product p ON p.id = con.product_id \n" +
            "JOIN category cate ON cate.id = p.category_id\n" +
            "JOIN status s ON con.status_id = s.id\n" +
            "JOIN customer cus ON con.customer_id = cus.id\n" +
            "WHERE con.status_id IN (1) and con.is_flag = 0  And con.id = ?;", nativeQuery = true)
    Contract findContractNotPayByID(int id);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param pageable the pageable to request a paged result, can be {@link Pageable#unpaged()}, must not be
     *          {@literal null}.
     * @return Page<Contract>
     */
    @Query("SELECT contract FROM Contract contract WHERE contract.isFlag = false")
    Page<Contract> findAll(Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param beginDate
     * @param endDate
     * @param status
     * @param id
     */
    @Modifying
    @Query("UPDATE Contract SET beginDate = ?1, endDate = ?2, status = ?3 WHERE id = ?4")
    void updateContract(LocalDate beginDate, LocalDate endDate, Status status, Integer id);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param id must not be {@literal null}.
     */
    @Modifying
    @Query("UPDATE Contract SET isFlag = true WHERE id = ?1")
    void deleteById(Integer id);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param id
     * @return contract
     */
    @Query("SELECT contract FROM Contract contract WHERE contract.id = ?1 AND contract.isFlag = false")
    Contract findAllById(Integer id);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param customerName
     * @param pageable
     * @return Page<Contract> found by name of customer
     */
    @Query("SELECT contract FROM Contract contract WHERE contract.customer.name LIKE %?1% AND contract.isFlag = false")
    Page<Contract> findByCustomerName(String customerName, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param productName
     * @param pageable
     * @return Page<Contract> found by name of product
     */
    @Query("SELECT contract FROM Contract contract WHERE contract.product.name LIKE %?1% AND contract.isFlag = false")
    Page<Contract> findByProductName(String productName, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param status
     * @param pageable
     * @return Page<Contract> found by status of contract
     */
    @Query("SELECT contract FROM Contract contract WHERE contract.status.name LIKE %?1% AND contract.isFlag = false")
    Page<Contract> findByStatus(String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param beforeDate
     * @param pageable
     * @return Page<Contract> found by beginDate of contract after beforeDate
     */
    @Query("SELECT contract FROM Contract contract WHERE contract.beginDate > ?1 AND contract.isFlag = false")
    Page<Contract> findByBeforeDate(LocalDate beforeDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param afterDate
     * @param pageable
     * @return Page<Contract> found by beginDate of contract before beforeDate
     */
    @Query("SELECT contract FROM Contract contract WHERE contract.beginDate < ?1 AND contract.isFlag = false")
    Page<Contract> findByAfterDate(LocalDate afterDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param customerName
     * @param productName
     * @param pageable
     * @return Page<Contract> found by customerName, productName of contract
     */
    @Query("SELECT contract FROM Contract contract WHERE contract.customer.name LIKE %?1% " +
            "AND contract.product.name LIKE %?2% AND contract.isFlag = false")
    Page<Contract> findByCustomerAndProduct(String customerName, String productName, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param customerName
     * @param beforeDate
     * @param pageable
     * @return Page<Contract> found by customerName of contract and beginDate of contract after beforeDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.beginDate > ?2 AND contract.isFlag = false")
    Page<Contract> findByCustomerAndBeforeDate(String customerName, LocalDate beforeDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param customerName
     * @param afterDate
     * @param pageable
     * @return Page<Contract> found by customerName of contract and beginDate of contract before afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.beginDate < ?2 AND contract.isFlag = false")
    Page<Contract> findByCustomerAndAfterDate(String customerName, LocalDate afterDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 12/05/2023
     * @param customerName
     * @param status
     * @param pageable
     * @return Page<Contract> found by customerName and Status of contract
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.status.name = ?2 AND contract.isFlag = false")
    Page<Contract> findByCustomerAndStatus(String customerName, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 13/05/2023
     * @param productName
     * @param beforeDate
     * @param pageable
     * @return Page<Contract> found by productName of contract and beginDate of contract after beforeDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.product.name LIKE %?1% AND contract.beginDate > ?2 AND contract.isFlag = false")
    Page<Contract> findByProductAndBeforeDate(String productName, LocalDate beforeDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 13/05/2023
     * @param productName
     * @param afterDate
     * @param pageable
     * @return Page<Contract> found by productName of contract and beginDate of contract before afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.product.name LIKE %?1% AND contract.beginDate < ?2 AND contract.isFlag = false")
    Page<Contract> findByProductAndAfterDate(String productName, LocalDate afterDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 13/05/2023
     * @param productName
     * @param status
     * @param pageable
     * @return Page<Contract> found by customerName and Status of contract
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.product.name LIKE %?1% AND contract.status.name = ?2 AND contract.isFlag = false")
    Page<Contract> findByProductAndStatus(String productName, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 13/05/2023
     * @param beforeDate
     * @param afterDate
     * @param pageable
     * @return Page<Contract> found by beginDate of contract through from beforeDate to afterDate
     */
    @Query("SELECT contract FROM Contract contract WHERE (contract.beginDate BETWEEN ?1 AND ?2) AND contract.isFlag = false")
    Page<Contract> findByBeginDateBetween(LocalDate beforeDate, LocalDate afterDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 13/05/2023
     * @param beforeDate
     * @param status
     * @param pageable
     * @return Page<Contract> found by Status of contract and beginDate of contract after beforeDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.beginDate > ?1 AND contract.status.name = ?2 AND contract.isFlag = false")
    Page<Contract> findByBeforeDateAndStatus(LocalDate beforeDate, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 13/05/2023
     * @param afterDate
     * @param status
     * @param pageable
     * @return Page<Contract> found by Status of contract and beginDate of contract before afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.beginDate < ?1 AND contract.status.name = ?2 AND contract.isFlag = false")
    Page<Contract> findByAfterDateAndStatus(LocalDate afterDate, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 15/05/2023
     * @param customerName
     * @param productName
     * @param beforeDate
     * @param pageable
     * @return Page<Contract> found by customerName and productName of contract and beginDate of contract after beforeDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.product.name LIKE %?2% AND contract.beginDate > ?3 " +
            "AND contract.isFlag = false")
    Page<Contract> findByCustomerAndProductAndBeforeDate(String customerName, String productName, LocalDate beforeDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 15/05/2023
     * @param customerName
     * @param productName
     * @param afterDate
     * @param pageable
     * @return Page<Contract> found by customerName and productName of contract and beginDate of contract before afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.product.name LIKE ?2 AND contract.beginDate < ?3 " +
            "AND contract.isFlag = false")
    Page<Contract> findByCustomerAndProductAndAfterDate(String customerName, String productName, LocalDate afterDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 15/05/2023
     * @param customerName
     * @param productName
     * @param status
     * @param pageable
     * @return Page<Contract> found by customerName, status and productName of contract
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.product.name LIKE %?2% AND contract.status.name = ?3 " +
            "AND contract.isFlag = false")
    Page<Contract> findByCustomerAndProductAndStatus(String customerName, String productName, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 15/05/2023
     * @param productName
     * @param beforeDate
     * @param afterDate
     * @param pageable
     * @return Page<Contract> found by productName of contract and beginDate of contract through from beforeDate to afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.product.name LIKE %?1% AND (contract.beginDate BETWEEN ?2 AND ?3) AND contract.isFlag = false")
    Page<Contract> findByProductAndBAndBeginDate(String productName, LocalDate beforeDate, LocalDate afterDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 15/05/2023
     * @param productName
     * @param beforeDate
     * @param status
     * @param pageable
     * @return  Page<Contract> found by productName, status of contract and beginDate of contract after beforeDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.product.name LIKE %?1% AND contract.beginDate > ?2 AND contract.status.name = ?3 " +
            "AND contract.isFlag = false")
    Page<Contract> findByProductAndBAndBeforeDateAndStatus(String productName, LocalDate beforeDate, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 15/05/2023
     * @param productName
     * @param afterDate
     * @param status
     * @param pageable
     * @return Page<Contract> found by productName, status of contract and beginDate of contract before afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.product.name LIKE %?1% AND contract.beginDate < ?2 AND contract.status.name = ?3 " +
            "AND contract.isFlag = false")
    Page<Contract> findByProductAndBAndAfterDateAndStatus(String productName, LocalDate afterDate, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 15/05/2023
     * @param status
     * @param beforeDate
     * @param afterDate
     * @param pageable
     * @return Page<Contract> found by status of contract and beginDate of contract through from beforeDate to afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.status.name = ?1 AND (contract.beginDate BETWEEN ?2 AND ?3) AND contract.isFlag = false")
    Page<Contract> findByStatusAndBAndBeginDate(String status, LocalDate beforeDate, LocalDate afterDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 16/05/2023
     * @param customerName
     * @param productName
     * @param beforeDate
     * @param afterDate
     * @param pageable
     * @return Page<Contract> found by customerName, productName of contract and beginDate of contract through from beforeDate to afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.product.name LIKE %?2% AND (contract.beginDate BETWEEN ?3 " +
            "AND ?4) AND contract.isFlag = false")
    Page<Contract> findByCustomerAndProductAndBeginDate(String customerName, String productName, LocalDate beforeDate, LocalDate afterDate, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 16/05/2023
     * @param customerName
     * @param productName
     * @param beforeDate
     * @param status
     * @param pageable
     * @return Page<Contract> found by customerName, productName, status of contract and beginDate of contract after beforeDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.product.name LIKE %?2% AND contract.beginDate > ?3 " +
            "AND contract.status.name = ?4 AND contract.isFlag = false")
    Page<Contract> findByCustomerAndProductAndBeforeDateAndStatus(String customerName, String productName, LocalDate beforeDate, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 16/05/2023
     * @param customerName
     * @param productName
     * @param afterDate
     * @param status
     * @param pageable
     * @return Page<Contract> found by customerName, productName, status of contract and beginDate of contract before afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.product.name LIKE %?2% AND contract.beginDate < ?3 " +
            "AND contract.status.name = ?4 AND contract.isFlag = false")
    Page<Contract> findByCustomerAndProductAndAfterDateAndStatus(String customerName, String productName, LocalDate afterDate, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 16/05/2023
     * @param productName
     * @param beforeDate
     * @param afterDate
     * @param status
     * @param pageable
     * @return Page<Contract> found by status, productName of contract and beginDate of contract through from beforeDate to afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.product.name LIKE %?1% AND (contract.beginDate BETWEEN ?2 AND ?3) AND contract.status.name = ?4 " +
            "AND contract.isFlag = false")
    Page<Contract> findByProductAndBeginDateAndStatus(String productName, LocalDate beforeDate, LocalDate afterDate, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 16/05/2023
     * @param customerName
     * @param beforeDate
     * @param afterDate
     * @param status
     * @param pageable
     * @return Page<Contract> found by status, customerName of contract and beginDate of contract through from beforeDate to afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND (contract.beginDate BETWEEN ?2 AND ?3) AND contract.status.name = ?4 " +
            "AND contract.isFlag = false")
    Page<Contract> findByCustomerAndBeginDateAndStatus(String customerName, LocalDate beforeDate, LocalDate afterDate, String status, Pageable pageable);

    /**
     * Create by PhongTD
     * Date created: 16/05/2023
     * @param customerName
     * @param productName
     * @param beforeDate
     * @param afterDate
     * @param status
     * @param pageable
     * @return Page<Contract> found by productName, status, customerName of contract and beginDate of contract through from beforeDate to afterDate
     */
    @Query("SELECT contract FROM Contract contract " +
            "WHERE contract.customer.name LIKE %?1% AND contract.product.name LIKE %?2% AND (contract.beginDate " +
            "BETWEEN ?3 AND ?4) AND contract.status.name = ?5 AND contract.isFlag = false")
    Page<Contract> findByCustomerAndProductAndBeginDateAndStatus(String customerName, String productName, LocalDate beforeDate, LocalDate afterDate, String status, Pageable pageable);

}
