package com.example.ShopSphere_product_service.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.math.BigDecimal;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDTO {
    @NotBlank
    private String name;
    private String description;
    @Min(value = 0)
    private BigDecimal price;
    private String image_url;
    private BigDecimal discount_price;
    @Min(value = 0)
    private Integer stock_quantity;
    private String category_id;
    private String brand_id;
    private String status;
}
