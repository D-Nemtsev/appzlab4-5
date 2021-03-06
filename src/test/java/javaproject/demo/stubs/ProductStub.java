package javaproject.demo.stubs;

import javaproject.demo.dto.ProductRequest;
import javaproject.demo.entities.Product;
import javaproject.demo.entities.Supplier;

import java.math.BigDecimal;
import java.util.HashSet;

public final class ProductStub {
    public static final Long ID = 1L;
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String BRAND = "brand";
    public static final String CODE = "code";
    public static final Integer PLACES_COUNT = 2;
    public static final BigDecimal PRICE = new BigDecimal("1233");
    public static final Supplier SUPPLIER = SupplierStub.getRandomSupplier();
    public static Product getRandomProduct() {
        return Product.builder()
                .id(ID)
                .name(NAME)
                .description(DESCRIPTION)
                .code(CODE)
                .brand(BRAND)
                .countOfAvailability(PLACES_COUNT)
                .price(PRICE)
                .supplier(SUPPLIER)
                .orders(new HashSet<>())
                .build();
    }
    public static ProductRequest getProductRequest() {
        var productRequest = new ProductRequest();
        productRequest.setName(NAME);
        productRequest.setDescription(DESCRIPTION);
        productRequest.setSupplier(SUPPLIER);
        productRequest.setPrice(PRICE);
        productRequest.setCountOfAvailability(PLACES_COUNT);
        return productRequest;
    }
}
