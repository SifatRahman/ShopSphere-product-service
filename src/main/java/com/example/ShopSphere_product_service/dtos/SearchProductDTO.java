package com.example.ShopSphere_product_service.dtos;

import lombok.*;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchProductDTO {
    private String name;
    private String description;
    private BigDecimal min_price;
    private BigDecimal max_price;
    private Boolean in_stock;
}
