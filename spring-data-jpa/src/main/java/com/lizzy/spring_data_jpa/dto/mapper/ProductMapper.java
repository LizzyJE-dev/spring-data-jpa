package com.lizzy.spring_data_jpa.dto.mapper;

import com.lizzy.spring_data_jpa.dto.CreateProductDto;
import com.lizzy.spring_data_jpa.dto.ProductDto;
import com.lizzy.spring_data_jpa.entity.Product;



public class ProductMapper {

    private ProductMapper() {}

    //Product -> ProductDto
    public static ProductDto toProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImage());
    }

    //CreateProductDto -> Product
    public static Product createToProduct (CreateProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        return product;
    }
}
