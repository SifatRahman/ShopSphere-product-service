package com.example.ShopSphere_product_service.services;

import com.example.ShopSphere_product_service.dtos.CreateProductDTO;
import com.example.ShopSphere_product_service.dtos.ProductResponseDTO;
import com.example.ShopSphere_product_service.dtos.SearchProductDTO;
import com.example.ShopSphere_product_service.dtos.UpdateProductDTO;
import com.example.ShopSphere_product_service.entities.Product;
import com.example.ShopSphere_product_service.repository.ProductRepository;
import com.example.ShopSphere_product_service.utils.ConstantUtils;
import com.example.ShopSphere_product_service.utils.exceptions.RequiredResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService extends BaseService {
    private final ProductRepository productRepository;

    public List<ProductResponseDTO> getProducts() {
        try {
            List<Product> products = productRepository.findAll();
            List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
            products.forEach(product -> {
                ProductResponseDTO responseDTO = ProductResponseDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .image_url(product.getImageUrl())
                        .discount_price(product.getDiscountPrice())
                        .stock_quantity(product.getStockQuantity())
                        .category_id(product.getCategoryId())
                        .brand_id(product.getBrandId())
                        .status(product.getStatus())
                        .created_at(product.getCreatedAt())
                        .updated_at(product.getUpdatedAt())
                        .build();
                productResponseDTOS.add(responseDTO);
            });
            return productResponseDTOS;
        } catch (Exception e) {
            processException(e);
        }
        return new ArrayList<>();
    }

    public ProductResponseDTO getProduct(String productId) {
        try {
            Product product = productRepository.findById(productId).orElseThrow(() -> new RequiredResourceNotFoundException("Product not found"));
            return ProductResponseDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .image_url(product.getImageUrl())
                    .discount_price(product.getDiscountPrice())
                    .stock_quantity(product.getStockQuantity())
                    .category_id(product.getCategoryId())
                    .brand_id(product.getBrandId())
                    .status(product.getStatus())
                    .created_at(product.getCreatedAt())
                    .updated_at(product.getUpdatedAt())
                    .build();
        } catch (Exception e) {
            processException(e);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void createProduct(@Valid CreateProductDTO dto) {
        try {
            if(dto.getDiscount_price().compareTo(dto.getPrice()) > 0){throw new RequiredResourceNotFoundException("Discount Price cannot be greater than the price of the product");}

            Product product = new Product();
            product.setId(ConstantUtils.getUUID());
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setImageUrl(dto.getImage_url());
            product.setDiscountPrice(dto.getDiscount_price());
            product.setStockQuantity(dto.getStock_quantity());
            product.setCategoryId(dto.getCategory_id());
            product.setBrandId(dto.getBrand_id());
            product.setStatus(dto.getStatus());
            product.setCreatedAt(LocalDateTime.now());

            productRepository.save(product);
        } catch (Exception e) {
            processException(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(String productId) {
        try {
            Product Product = productRepository.findById(productId).orElseThrow(() -> new RequiredResourceNotFoundException("Product not found"));
            productRepository.delete(Product);
        } catch (Exception e) {
            processException(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProduct(@RequestBody @Valid UpdateProductDTO dto) {
        try {
            Product product = productRepository.findById(dto.getProduct_id()).orElseThrow(() -> new RequiredResourceNotFoundException("Product not found"));
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());
            product.setImageUrl(dto.getImage_url());
            product.setDiscountPrice(dto.getDiscount_price());
            product.setStockQuantity(dto.getStock_quantity());
            product.setCategoryId(dto.getCategory_id());
            product.setBrandId(dto.getBrand_id());
            product.setStatus(dto.getStatus());
            product.setUpdatedAt(LocalDateTime.now());

            productRepository.save(product);
        } catch (Exception e) {
            processException(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public List<ProductResponseDTO> searchProduct(SearchProductDTO dto) {
        try {
            Pageable pageable = PageRequest.of(0, 10);

            List<Product> products = productRepository.searchProducts(entityManager, dto, pageable);

            return products.stream().map(product -> ProductResponseDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .image_url(product.getImageUrl())
                    .discount_price(product.getDiscountPrice())
                    .stock_quantity(product.getStockQuantity())
                    .category_id(product.getCategoryId())
                    .brand_id(product.getBrandId())
                    .status(product.getStatus())
                    .created_at(product.getCreatedAt())
                    .updated_at(product.getUpdatedAt())
                    .build()).collect(Collectors.toList());
        } catch (Exception e) {
            processException(e);
        }
        return List.of();
    }

}
