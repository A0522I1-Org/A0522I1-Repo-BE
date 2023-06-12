package com.example.spring_pawn_app.repository.customer;

import com.example.spring_pawn_app.dto.contract.CustomerListDto;
import com.example.spring_pawn_app.dto.customer.CustomerDTO;
import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select c.id, c.customer_code, c.delete_time, c.phone_number,c.customer_name, c.gender, c.id_card, c.address, c.avatar, c.date_of_birth, c.email, c.is_flag, c.note, c.status " +
            "from customer as c " +
            "where c.id = :id and c.is_flag = 0", nativeQuery = true)
    Customer findCustomerById(@Param("id") int id);

    @Query(value = "select c.id, c.customer_code, c.customer_name, c.gender, c.phone_number, c.id_card, c.address, c.email " +
            "from customer as c " +
            "where c.customer_name like %:nameCustomer% and c.is_flag = 0",
            countQuery = "select c.id, c.customer_code, c.customer_name, c.gender, c.phone_number, c.id_card, c.address, c.email " +
                    "from customer as c " +
                    "where c.customer_name like %:nameCustomer% and c.is_flag = 0", nativeQuery = true)
    Page<CustomerListDto> findAllCustomerWithPage(Pageable pageable, @Param("nameCustomer") String nameCustomer);

    /**
     * Created by: PhongTD
     * Date created: 16/05/2023
     *
     * @param customerNameEdit
     * @param id
     */
    @Query("UPDATE Customer SET name = ?1 WHERE id = ?2")
    @Modifying
    void updateCustomerName(String customerNameEdit, Integer id);

    /**
     * @author Trần Thế Huy
     * @version 2
     * @since 6/6/2023
     */
    @Query(value = "SELECT c.id, c.customerCode, c.name, c.gender, c.dateOfBirth, c.phone, c.identityCard, COALESCE(COUNT(con.id), 0) " +
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

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * Function: search customer
     * //   * @param id
     */
    @Query(value = "select id,address,avatar,customer_code,phone_number,date_of_birth,email,gender,id_card,is_flag, customer_name,note,status, delete_time from customer where customer_name like %?% and is_flag = 0 ", nativeQuery = true)
    Page<Customer> findByCustomer(String customer_name, PageRequest page);

    /**
     * <<<<<<< HEAD
     *
     * @author TuanVD
     * @version 1
     * @since 06/06/2023
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO customer (customer_code, name, gender, date_of_birth," +
            "id_card, phone, email, address, avatar) " +
            "VALUES (:#{#customerDTO.customerCode}, :#{#customerDTO.name}, :#{#customerDTO.gender}, " +
            ":#{#customerDTO.dateOfBirth}, :#{#customerDTO.identityCard}, :#{#customerDTO.phone}, " +
            ":#{#customerDTO.email}, :#{#customerDTO.address}, :#{#customerDTO.avatar})", nativeQuery = true)
    void createCustomer(@Param("customerDTO") CustomerDTO customerDTO);

    @Modifying
    @Transactional
    @Query(value = "UPDATE customer " +
            "SET customer_code = :#{#customerDTO.customerCode}, " +
            "name = :#{#customerDTO.name}, " +
            "gender = :#{#customerDTO.gender}, " +
            "date_of_birth = :#{#customerDTO.dateOfBirth}, " +
            "id_card = :#{#customerDTO.identityCard}, " +
            "phone = :#{#customerDTO.phone}, " +
            "email = :#{#customerDTO.email}, " +
            "address = :#{#customerDTO.address}, " +
            "avatar = :#{#customerDTO.avatar} " +
            "WHERE id = :#{#customerDTO.id}", nativeQuery = true)
    void updateCustomer(@Param("customerDTO") CustomerDTO customerDTO);

    @Query(value = "select email from customer where email = ?1", nativeQuery = true)
    String existsByCustomerEmail(String email);

    @Query(value = "select phone from customer where phone = ?1", nativeQuery = true)
    String existsByCustomerPhone(String phoneNumber);

    @Query(value = "select id_card from customer where id_card = ?1", nativeQuery = true)
    String existsByCustomerIdentityCard(String idCard);

    @Query(value = "select customer_code from customer where customer_code = ?1", nativeQuery = true)
    String existsByCustomerCode(String code);

    /**
     * Created by: ManPD
     * Date create: 6/6/2023
     *
     * @param customerCode
     * @return customer
     */
    @Query(value = "select c.id, c.customer_name, c.customer_code, c.id_card, c.address, c.note, c.is_flag, c.email, c.phone_number from customer c where c.customer_code like ?1 and c.is_flag = 0", nativeQuery = true)
    Customer findCustomerByCustomerCode(String customerCode);


}
