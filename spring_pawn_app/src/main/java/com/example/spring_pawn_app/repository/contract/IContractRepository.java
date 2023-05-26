package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

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
    @Query(value = "update contract as c set c.status_id = 3 where c.id = :id", countQuery = "update contract as c set c.status_id = 3 where c.id = :id", nativeQuery = true)
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
    Page<Contract> findAll(Pageable pageable);
}