package com.example.ShopSphere_product_service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseModelDTO  {
    private String status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String correlation_id;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private Object data;

}