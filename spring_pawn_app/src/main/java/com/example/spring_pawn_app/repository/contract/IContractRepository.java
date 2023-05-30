package com.example.spring_pawn_app.repository.contract;

import com.example.spring_pawn_app.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Repository
@Transactional
public interface IContractRepository extends JpaRepository<Contract, Integer>{

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
