package com.example.spring_pawn_app.repository.customer;

import com.example.spring_pawn_app.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{
    @Query(value = "select c.id, c.customer_code, c.customer_name, c.gender, c.phone, c.id_card, c.address, c.avatar, c.date_of_birth, c.email, c.is_flag, c.note, c.status "+
                   "from customer as c "+
                   "where c.customer_name like %:nameCustomer% and c.is_flag = 0",
            countQuery = "select c.id, c.customer_code, c.customer_name, c.gender, c.phone, c.id_card, c.address, c.avatar, c.date_of_birth, c.email, c.is_flag, c.note, c.status "+
                    "from customer as c "+
                    "where c.customer_name like %:nameCustomer% and c.is_flag = 0", nativeQuery = true)
    Page<Customer> findAllCustomerWithPage(Pageable pageable, @Param("nameCustomer") String nameCustomer);
}
//"select c.id, c.customer_code, c.customer_name, c.gender, c.phone, c.id_card, c.address "+
//        "from customer as c "+
//        "where c.customer_name like %:nameCustomer%"