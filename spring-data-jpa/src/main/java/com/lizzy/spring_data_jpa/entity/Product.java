package com.lizzy.spring_data_jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product Name can't be empty.")
    private String name;

    @NotBlank(message = "Product Description can't be empty.")
    private String description;

    @NotNull(message = "Product Name can't be null.")
    @PositiveOrZero(message = "Price has to be positive or zero.")
    private Double price;

    @NotBlank(message = "Image can't be empty.")
    private String image;
}
