package com.example.ShopSphere_product_service.dtos.rabbit;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventCommunicationDTO {
    private String eventName;
    private Object payload;
}
