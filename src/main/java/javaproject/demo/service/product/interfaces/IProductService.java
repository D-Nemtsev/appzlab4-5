package javaproject.demo.service.product.interfaces;

import javaproject.demo.dto.ProductRequest;
import javaproject.demo.dto.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> getAll();
    List<ProductResponse> getProductsBySupplierId(Long id);
    ProductResponse getById(Long id);
    ProductResponse create(ProductRequest product);
    ProductResponse update(Long id, ProductRequest product);
    void delete(Long id);
}
