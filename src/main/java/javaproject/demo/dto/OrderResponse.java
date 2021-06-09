package javaproject.demo.dto;

import javaproject.demo.entities.Order;
import lombok.Data;

import java.util.Date;

@Data
public class OrderResponse {
    private Long id;
    private String description;
    private Date createdDate;
    private Long product_id;
    private Long client_id;

    public static OrderResponse mapToOrderResponse(Order order) {
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setDescription(order.getDescription());
        orderResponse.setCreatedDate(order.getCreatedDate());
        orderResponse.setProduct_id(order.getProduct().getId());
        orderResponse.setClient_id(order.getClient().getId());

        return orderResponse;
    }

}
