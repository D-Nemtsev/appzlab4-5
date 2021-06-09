package javaproject.demo.dto;

import javaproject.demo.entities.Supplier;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String code;
    private String brand;
    private Integer countOfAvailability;
    private BigDecimal price;
    private Supplier supplier;
}
