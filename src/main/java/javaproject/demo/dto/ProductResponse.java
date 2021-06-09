package javaproject.demo.dto;

import javaproject.demo.entities.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String code;
    private String brand;
    private Integer countOfAvailability;
    private BigDecimal price;
    private SupplierResponse supplier;

    public static ProductResponse mapToProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(product.getId());
        productResponse.setDescription(product.getDescription());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setBrand(product.getBrand());
        productResponse.setCode(product.getCode());
        productResponse.setCountOfAvailability(product.getCountOfAvailability());
        productResponse.setSupplier(SupplierResponse.mapToSupplierResponse(product.getSupplier()));

        return productResponse;
    }

}
