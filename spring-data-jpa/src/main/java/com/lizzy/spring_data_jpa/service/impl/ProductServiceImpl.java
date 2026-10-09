package com.lizzy.spring_data_jpa.service.impl;

import com.lizzy.spring_data_jpa.dto.CreateProductDto;
import com.lizzy.spring_data_jpa.dto.ProductDto;
import com.lizzy.spring_data_jpa.dto.UpdateProductDto;
import com.lizzy.spring_data_jpa.dto.mapper.ProductMapper;
import com.lizzy.spring_data_jpa.entity.Product;
import com.lizzy.spring_data_jpa.exception.ProductNotFoundException;
import com.lizzy.spring_data_jpa.repository.ProductRepository;
import com.lizzy.spring_data_jpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    //Get All Products
    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(ProductMapper::toProductDto).toList();
    }

    //Get Product By ID
    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Requested product with ID: " + id + " doesn't exist."));
        return ProductMapper.toProductDto(product);
    }

    //Add Products
    @Override
    public ProductDto createProduct(CreateProductDto createProductDto) {
        Product product = ProductMapper.createToProduct(createProductDto);
        productRepository.save(product);
        return ProductMapper.toProductDto(product);
    }

    //Update Products
    @Override
    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Requested product with ID: "+ id + " doesn't exist."));
        product.setName(updateProductDto.getName());
        product.setDescription(updateProductDto.getDescription());
        product.setPrice(updateProductDto.getPrice());
        product.setImage(updateProductDto.getImage());
        productRepository.save(product);
        return ProductMapper.toProductDto(product);
    }

    //Delete Product By ID
    @Override
    public void deleteProductById(Long id) {

        if (!productRepository.existsById(id)){
            throw new ProductNotFoundException("Requested product with ID: " + id + " doesn't exist.");
        }
        productRepository.deleteById(id);
    }
}
