package com.example.ShopSphere_product_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {
    @NotNull
    @Id
    @Size(max = 128)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false, columnDefinition = "nvarchar")
    private String name;

    @Column(name = "description", columnDefinition = "nvarchar(max)")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    @Transient
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "brand_id")
    private String brandId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
