package com.example.spring_pawn_app.service.product;
import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;


import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.repository.role.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Autowired
    private IContractRepository iContractRepository;

    @Override
    public void save(Product product) {
        iProductRepository.save(product);
    }

    /**
     * Created by: NamHV
     * Date create: 3/6/2023
     * */
    @Override
    public List<Product> findAllByCustomer(Integer id) {
        Customer customer = iCustomerRepository.findCustomerById( id );
        List<Contract> contracts = iContractRepository.findContractByCustomerId( id );
        List<Product> products = new ArrayList<>();
        for (Contract contract : contracts) {
            products.add( contract.getProduct() );
        }
        return products;
    }
}
