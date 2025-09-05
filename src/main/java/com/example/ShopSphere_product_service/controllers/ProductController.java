package com.example.ShopSphere_product_service.controllers;



import com.example.ShopSphere_product_service.RabbitMq.RabbitTestProducer;
import com.example.ShopSphere_product_service.dtos.*;
import com.example.ShopSphere_product_service.services.ProductService;
import com.example.ShopSphere_product_service.utils.enums.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
@Validated
@RequiredArgsConstructor
public class ProductController extends BaseController {
    private final ProductService productService;
    private final RabbitTestProducer rabbitTestProducer;

    @Tag(name = "PS001")
    @GetMapping("/products")
    public ResponseEntity<ResponseModelDTO> getProducts() {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try {
            List<ProductResponseDTO> productResponseDTOList = productService.getProducts();
            responseModelDTO.setStatus(Status.success.toString());
            responseModelDTO.setMessage("Products found successfully");
            responseModelDTO.setData(productResponseDTOList);
            return ResponseEntity.ok(responseModelDTO);

        } catch (Exception e) {
            return exceptionTask(e);
        }
    }

    @Tag(name = "PS002")
    @GetMapping("/products/{product_id}")
    public ResponseEntity<ResponseModelDTO> getProduct(@PathVariable("product_id") String productId) {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try {
            ProductResponseDTO productResponseDTO = productService.getProduct(productId);
            responseModelDTO.setStatus(Status.success.toString());
            responseModelDTO.setMessage("Product found successfully");
            responseModelDTO.setData(productResponseDTO);
            return ResponseEntity.ok(responseModelDTO);

        } catch (Exception e) {
            return exceptionTask(e);
        }
    }

    @Tag(name = "PS003")
    @PostMapping("/products")
    public ResponseEntity<ResponseModelDTO> createProduct(
            @RequestBody @Valid CreateProductDTO dto) {
        try {
            productService.createProduct(dto);
            return ResponseEntity.ok(
                    ResponseModelDTO.builder()
                            .status(Status.success.toString())
                            .message("Product created successfully!")
                            .data(null)
                            .build()
            );
        } catch (Exception e) {
            return exceptionTask(e);
        }
    }
    @Tag(name = "PS004")
    @DeleteMapping("/products/{product_id}")
    public ResponseEntity<ResponseModelDTO> deleteProduct(@PathVariable("product_id") String productId) {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try {
            productService.deleteProduct(productId);
            responseModelDTO.setStatus(Status.success.toString());
            responseModelDTO.setMessage("Product deleted successfully");
            responseModelDTO.setData(null);
            return ResponseEntity.ok(responseModelDTO);
        } catch (Exception e) {
            return exceptionTask(e);
        }
    }
    @Tag(name = "PS005")
    @PutMapping("/products")
    public ResponseEntity<ResponseModelDTO> updateProduct(@RequestBody @Valid UpdateProductDTO dto) {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try {
            productService.updateProduct(dto);
            responseModelDTO.setStatus(Status.success.toString());
            responseModelDTO.setMessage("Product updated successfully");
            responseModelDTO.setData(null);
            return ResponseEntity.ok(responseModelDTO);

        } catch (Exception e) {
            return exceptionTask(e);
        }
    }

    @Tag(name = "PS006")
    @PostMapping("/products/search")
    public ResponseEntity<ResponseModelDTO> searchProduct(@RequestBody @Valid SearchProductDTO dto) {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try {
            List<ProductResponseDTO> responseDTOS = productService.searchProduct(dto);
            responseModelDTO.setStatus(Status.success.toString());
            responseModelDTO.setMessage("Product search found successfully");
            responseModelDTO.setData(responseDTOS);
            return ResponseEntity.ok(responseModelDTO);

        } catch (Exception e) {
            return exceptionTask(e);
        }
    }

    @Tag(name = "RT001")
    @GetMapping("/send")
    public ResponseEntity<ResponseModelDTO> sendToMQ() {
        ResponseModelDTO responseModelDTO = new ResponseModelDTO();
        try {
            Object sentObj = rabbitTestProducer.sendTestMessageToMQ();
            responseModelDTO.setStatus(Status.success.toString());
            responseModelDTO.setMessage("Rabbit message sent successfully");
            responseModelDTO.setData("Message : "+sentObj);
            return ResponseEntity.ok(responseModelDTO);

        } catch (Exception e) {
            return exceptionTask(e);
        }
    }
}