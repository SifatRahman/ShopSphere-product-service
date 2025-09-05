package com.example.ShopSphere_product_service.dtos;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDTO {
    private String product_id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image_url;
    private BigDecimal discount_price;
    private Integer stock_quantity;
    private String category_id;
    private String brand_id;
    private String status;
}
