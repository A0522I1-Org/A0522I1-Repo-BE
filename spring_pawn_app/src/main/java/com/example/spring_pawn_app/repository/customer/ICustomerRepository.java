package com.example.spring_pawn_app.repository.customer;

import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

  /**
   * Created by: NamHV
   * Date create: 3/6/2023
   * Function: search customer
//   * @param id
   */
  @Query(value = "select id,address,avatar,customer_code,date_of_birth,email,gender,id_card,is_flag, customer_name,note,phone,status from customer where customer_name like %?% and is_flag = 0 ",nativeQuery = true)
  Page<Customer> findByCustomer(String customer_name, PageRequest page);
/**
 * Created by: NamHV
 * Date create: 3/6/2023
 * */
  @Query(value = "select id,address,avatar,customer_code,date_of_birth,email,gender,id_card,is_flag, customer_name,note,phone,status from customer where id like %?% and is_flag = 0",nativeQuery = true  )
  Customer findCustomerById(Integer id);
}
