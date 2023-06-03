package com.example.spring_pawn_app.service.product;

import com.example.spring_pawn_app.model.Contract;
import com.example.spring_pawn_app.model.Customer;
import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.repository.contract.IContractRepository;
import com.example.spring_pawn_app.repository.customer.ICustomerRepository;
import com.example.spring_pawn_app.repository.product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository iProductRepository;

    @Autowired
    private ICustomerRepository iCustomerRepository;
    @Autowired
    private IContractRepository iContractRepository;


    @Override
    public List<Product> findAllByCustomer(Integer id) {
        Customer customer = iCustomerRepository.findCustomerById( id );
        List<Contract> contracts = iContractRepository.findContractByCustomerId( id );
        List<Product> products = new ArrayList<>();
        for (Contract contract : contracts) {
            products.add( contract.getProduct() );
        }
        ;
        return products;
    }

    @Override
    public List<Product> findByProduct(String name, Double price, String nameCategory) {
        List<Product> listProduct = new ArrayList<>();
        if (!name.equals( "" ) && price != 0 && !nameCategory.equals( "" )) {
            listProduct = iProductRepository.findByProduct( name, price, nameCategory );
        } if (!name.equals( "" ) && price == 0 && nameCategory.equals( "" )) {
            listProduct = iProductRepository.findByProductName( name );
        } if (name.equals( "" ) && price != 0 && nameCategory.equals( "" )) {
            listProduct= iProductRepository.findByProductPrice( price );
        }if (name.equals( "" ) && price == 0 && !nameCategory.equals( "" )) {
            listProduct = iProductRepository.findByProductCategoryName( nameCategory );
        }  if (!name.equals( "" ) && price != 0 && nameCategory.equals( "" )) {
            listProduct = iProductRepository.findByProductNameAndPrice( name, price );
        }if (!name.equals( "" ) && price == 0  && !nameCategory.equals( "" )) {
            listProduct = iProductRepository.findByProductNameAndCategoryName( name, nameCategory );
        }if (name.equals( "" ) && price != 0 && !nameCategory.equals( "" )) {
            listProduct = iProductRepository.findByProductPriceAndCategoryName( price, nameCategory );
        } if(name.equals( "" ) && price == 0 && nameCategory.equals( "" )){
            listProduct = iProductRepository.findAll();
        }
      return listProduct;
    }

}



