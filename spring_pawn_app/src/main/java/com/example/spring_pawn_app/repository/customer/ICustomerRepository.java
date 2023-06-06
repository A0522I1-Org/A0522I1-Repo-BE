package com.example.spring_pawn_app.repository.customer;

import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * @author Trần Thế Huy
     * @version 2
     * @since 6/6/2023
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
