package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private IProductService iProdcutService;

    @GetMapping("products/customer/{id}")
    public List<Product> findProductByCustomer(@PathVariable("id") Integer id) {
        return iProdcutService.findAllByCustomer( id );
    }


    @GetMapping("products")
    public List<Product> findProduct(@RequestParam(value = "name",defaultValue = "") String name,
    @RequestParam(value = "price",defaultValue = "0") Double price, @RequestParam(value = "nameCategory",defaultValue = "") String nameCategory){
        return iProdcutService.findByProduct( name, price, nameCategory );
    }
}
