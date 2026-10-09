package com.lizzy.spring_data_jpa.controller;

import com.lizzy.spring_data_jpa.dto.CreateProductDto;
import com.lizzy.spring_data_jpa.dto.ProductDto;
import com.lizzy.spring_data_jpa.dto.UpdateProductDto;
import com.lizzy.spring_data_jpa.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class ProductController {

    private final ProductService productService;

    //GET ALL PRODUCTS
    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    //GET PRODUCT BY ID
    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    //ADD/CREATE PRODUCTS
    @PostMapping(path = "/products")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody CreateProductDto productDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));
    }

    //UPDATE PRODUCT
    @PutMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id ,@Valid @RequestBody UpdateProductDto updateProductDto) {
        return ResponseEntity.ok(productService.updateProduct(id,updateProductDto));
    }

    //DELETE PRODUCTS BY ID
    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
