package javaproject.demo.mapper;

import javaproject.demo.dto.ProductRequest;
import javaproject.demo.entities.Product;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;

@Component
public class ProductMapper {
    public Product fromRequest(ProductRequest request) {
        return Product.builder()
                .id(new Random().nextLong())
                .description(request.getDescription())
                .name(request.getName())
                .countOfAvailability(request.getCountOfAvailability())
                .price(request.getPrice())
                .brand(request.getBrand())
                .code(request.getCode())
                .supplier(request.getSupplier())
                .orders(new HashSet<>())
                .build();
    }
}
