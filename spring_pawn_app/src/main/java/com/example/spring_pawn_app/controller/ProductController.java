package com.example.spring_pawn_app.controller;

import com.example.spring_pawn_app.model.Product;
import com.example.spring_pawn_app.service.product.IProdcutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private IProdcutService iProdcutService;
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findAll(@RequestParam(value = "name",defaultValue = "") String name,
    @RequestParam(value = "price",defaultValue = "") String price, @RequestParam(value = "categoryId",defaultValue = "") String categoryId,
                                                 @RequestParam(defaultValue = "0") int page) {
        Page<Product> productPage = iProdcutService.findByProduct(name,price,categoryId,PageRequest.of( page,5 ) );
        if (productPage == null){
            return  new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Page<Product>>( productPage,HttpStatus.OK);
    }
}
