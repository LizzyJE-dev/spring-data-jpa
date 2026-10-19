package com.lizzy.spring_data_jpa.service.impl;


import com.lizzy.spring_data_jpa.dto.ProductDto;
import com.lizzy.spring_data_jpa.entity.Product;
import com.lizzy.spring_data_jpa.exception.ProductNotFoundException;
import com.lizzy.spring_data_jpa.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

    @Test
    public void getProductById_throwsException_whenProductDoesNotExist() {
        when(productRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.getProductById(100L));
    }
}
