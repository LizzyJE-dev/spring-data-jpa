package com.lizzy.spring_data_jpa.service.impl;


import com.lizzy.spring_data_jpa.dto.CreateProductDto;
import com.lizzy.spring_data_jpa.dto.ProductDto;
import com.lizzy.spring_data_jpa.dto.UpdateProductDto;
import com.lizzy.spring_data_jpa.entity.Product;
import com.lizzy.spring_data_jpa.exception.ProductNotFoundException;
import com.lizzy.spring_data_jpa.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*
JUnit Assertions.
------------------------------------------------------------------------------------------------------------------------
assertEquals(expected, actual): Asserts that the expected and actual values are equal.
assertNotEquals(expected, actual): Asserts that the expected values and the actual values are not equal.
assertTrue(condition): This asserts whether the given condition is true. Test case passes if it's true and fails if not.
assertFalse(condition): This asserts whether the given condition is false. Test case passes if it's false and fails if not.
assertNull(value): This asserts whether the given value is null. Test case passes if it's null and fails if not.
assertNotNull(value): This asserts whether the given value is not null. Test case passes if it's not null and fails if not.
assertArrayEquals(expectedArray, actualArray): This asserts whether the expected and actual arrays are equal.
Test case passes if they're equal and fails if not.
assertSame(expected, actual): This asserts whether the expected and actual references point to the same object.
Test case passes if it's true and fails if not.
assertNotSame(expected, actual): This asserts whether the expected and actual references do not point to the same object.
Test case passes if it's true and fails if not.
assertThrows(exceptionType, executable): This asserts whether the executable throws an exception of the specified type.
Test case passes if it throws an exception and fails if not.
------------------------------------------------------------------------------------------------------------------------
*/

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    //Get all products
    @Test
    public void getAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("product1");
        product1.setDescription("product1 y ya");
        product1.setPrice(5000.0);
        product1.setImage("Wip");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("product2");
        product2.setDescription("product2 y ya");
        product2.setPrice(3000.0);
        product2.setImage("Wip");

        when(productRepository.findAll()).thenReturn(List.of(product1,product2));

        List<ProductDto> productDto = productServiceImpl.getProducts();

        assertNotNull(productDto);
        assertEquals(2, productDto.size());
        assertEquals("product1", productDto.get(0).getName());
        assertEquals("product2", productDto.get(1).getName());
    }

    //Get all product with an empty list
    @Test
    public void getAllProducts_returnEmptyList_whenNoProductsFound() {
        when(productRepository.findAll()).thenReturn(List.of());
        List<ProductDto> productDto = productServiceImpl.getProducts();

        assertNotNull(productDto);
        assertTrue(productDto.isEmpty());
    }


    //Get Product by existing id
    @Test
    public void getProductById_ReturnProductDto_ifExists() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Keyboard");
        product.setDescription("Wireless Keyboard");
        product.setPrice(80000.0);
        product.setImage("Wip");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductDto productDto = productServiceImpl.getProductById(1L);

        assertNotNull(productDto);
        assertEquals("Keyboard", productDto.getName());
        assertEquals("Wireless Keyboard", productDto.getDescription());
        assertEquals(80000.0, productDto.getPrice());
        assertEquals("Wip", productDto.getImage());
    }

    //Get product when id does not exist
    @Test
    public void getProductById_throwsException_whenProductDoesNotExist() {
        when(productRepository.findById(100L)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.getProductById(100L));
    }

    //Add products
    @Test
    public void createProduct() {
        CreateProductDto createProductDto = new CreateProductDto();
        createProductDto.setName("CPU");
        createProductDto.setDescription("Core Ultra 9");
        createProductDto.setPrice(5000000.0);
        createProductDto.setImage("Wip");

        when(productRepository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductDto productDtoRes = productServiceImpl.createProduct(createProductDto);
        assertNotNull(productDtoRes);
        assertEquals("CPU", productDtoRes.getName());
        assertEquals("Core Ultra 9", productDtoRes.getDescription());
        assertEquals(5000000.0, productDtoRes.getPrice());
        assertEquals("Wip", productDtoRes.getImage());
    }


    //Update products
    @Test
    public void updateProduct() {
        Long id = 1L;
        Product product = new Product();
        product.setName("CPU");
        product.setDescription("Intel Core Ultra 9");
        product.setPrice(5000000.0);
        product.setImage("Wip");

        UpdateProductDto updateNewProductDto = new UpdateProductDto();
        updateNewProductDto.setName("CPU");
        updateNewProductDto.setDescription("Core Ultra 9 275HX");
        updateNewProductDto.setPrice(5000000.0);
        updateNewProductDto.setImage("Wip");

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        ProductDto productDtoRes = productServiceImpl.updateProduct(id, updateNewProductDto);

        assertNotNull(productDtoRes);
        assertEquals("CPU", productDtoRes.getName());
        assertEquals("Core Ultra 9 275HX", productDtoRes.getDescription());
        assertEquals(5000000.0, productDtoRes.getPrice());
        assertEquals("Wip", productDtoRes.getImage());
    }

    @Test
    public void updateProduct_throwsException_whenProductDoesNotExist() {
        Long id = 100L;
        UpdateProductDto updateNewProductDto = new UpdateProductDto();
        updateNewProductDto.setName("Gaming Keyboard");
        updateNewProductDto.setDescription("Wireless Keyboard");
        updateNewProductDto.setPrice(5000000.0);
        updateNewProductDto.setImage("Wip");

        when(productRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.updateProduct(id, updateNewProductDto));

        verify(productRepository, never()).save(any(Product.class));
    }

    //Delete products
    @Test
    public void deleteProductById() {
        Long id = 1L;

        when(productRepository.existsById(id)).thenReturn(Boolean.TRUE);
        productServiceImpl.deleteProductById(id);
        verify(productRepository).deleteById(id);
    }

    //Delete product when id does not exist
    @Test
    public void deleteProductById_throwsException_whenProductDoesNotExist() {
        Long id = 100L;
        when(productRepository.existsById(id)).thenReturn(Boolean.FALSE);
        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.deleteProductById(id));
        verify(productRepository, never()).deleteById(id);
    }

}
