package com.example.spring_pawn_app.repository.customer;

import com.example.spring_pawn_app.dto.CustomerListDto;
import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{
    @Query(value = "select c.id, c.customer_code, c.customer_name, c.gender, c.phone, c.id_card, c.address, c.avatar, c.date_of_birth, c.email, c.is_flag, c.note, c.phone, c.status " +
                   "from customer as c " +
                   "where c.id = :id and c.is_flag = 0", nativeQuery = true)
    Customer findCustomerById(@Param("id") int id);
    @Query(value = "select c.id, c.customer_code, c.customer_name, c.gender, c.phone, c.id_card, c.address, c.email "+
                   "from customer as c "+
                   "where c.customer_name like %:nameCustomer% and c.is_flag = 0",
            countQuery = "select c.id, c.customer_code, c.customer_name, c.gender, c.phone, c.id_card, c.address, c.email "+
                    "from customer as c "+
                    "where c.customer_name like %:nameCustomer% and c.is_flag = 0", nativeQuery = true)
    Page<CustomerListDto> findAllCustomerWithPage(Pageable pageable, @Param("nameCustomer") String nameCustomer);
/**
 * @author Trần Thế Huy
 * @version 1
 * @since 28/5/2023
 */
    @Query(value = "SELECT c.id, c.customerCode, c.name, c.gender, c.dateOfBirth, c.phone, c.identityCard, COUNT(con.id) " +
            "FROM Customer c " +
            "LEFT JOIN Contract con ON c.id = con.customer.id " +
            "WHERE ((CONCAT(c.customerCode, c.name, c.phone, c.identityCard) LIKE %?1%) " +
            "AND (?2 IS NULL OR c.dateOfBirth = ?2) " +
            "AND (?3 IS NULL OR c.gender = ?3)) " +
            "AND c.isFlag = FALSE " +
            "GROUP BY c.id")
    Page<Object[]> getAllWithRequirement(String valueReceived, LocalDate searchDateOfBirth, Integer searchGender, Pageable pageable);

    @Query(value = "SELECT c.id, c.customerCode, c.name, c.phone, c.identityCard, c.deleteTime " +
            "FROM Customer c " +
            "WHERE (CONCAT(c.customerCode, c.name, c.phone, c.identityCard) LIKE %?1%) " +
            "AND c.isFlag = TRUE " +
            "GROUP BY c.id")
    Page<Object[]> getAllWithRequirementInRestore(String valueReceived, Pageable pageable);

    @Query(value = "SELECT c.id, c.customerCode, c.name, c.gender, c.dateOfBirth, c.identityCard, c.phone, c.email, " +
            "c.address, c.avatar, c.note, COUNT(con.id) " +
            "FROM Customer c " +
            "LEFT JOIN Contract con ON con.customer.id = c.id " +
            "WHERE c.id = ?1 AND c.isFlag = FALSE")
    List<Object[]> getCustomerById(Integer id);

    @Query(value = "SELECT c.id, c.customerCode, c.name, c.gender, c.dateOfBirth, c.identityCard, c.phone, " +
            "c.email, c.address, c.avatar, c.note, " +
            "COUNT(con.id) AS contract_count " +
            "FROM Customer c " +
            "LEFT JOIN Contract con ON con.customer.id = c.id " +
            "WHERE c.id = ?1 AND c.isFlag = TRUE")
    List<Object[]> getCustomerByIdInRestore(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Customer c SET c.isFlag = TRUE, c.deleteTime = CURRENT_TIMESTAMP WHERE c.id = ?1 AND c.isFlag = FALSE")
    void deleteCustomerById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Customer c SET c.isFlag = FALSE WHERE c.id = ?1 AND c.isFlag = TRUE")
    void restoreCustomerById(Integer id);
}
