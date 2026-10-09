package com.lizzy.spring_data_jpa.service;

import com.lizzy.spring_data_jpa.dto.CreateProductDto;
import com.lizzy.spring_data_jpa.dto.ProductDto;
import com.lizzy.spring_data_jpa.dto.UpdateProductDto;
import com.lizzy.spring_data_jpa.entity.Product;

import java.util.List;


public interface ProductService {
    public List<ProductDto> getProducts();
    public ProductDto getProductById(Long id);
    public ProductDto createProduct(CreateProductDto productdto);
    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto);
    public void deleteProductById(Long id);
}
