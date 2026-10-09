package com.lizzy.spring_data_jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {
    @NotBlank(message = "Product Name can't be empty.")
    private String name;

    @NotBlank(message = "Product Description can't be empty.")
    private String description;

    @NotNull(message = "Product price can't be null.")
    @PositiveOrZero(message = "Price has to be positive or zero.")
    private Double price;

    @NotBlank(message = "Image can't be empty.")
    private String image;
}
