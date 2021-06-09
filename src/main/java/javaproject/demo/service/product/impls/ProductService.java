package javaproject.demo.service.product.impls;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javaproject.demo.dto.ProductRequest;
import javaproject.demo.dto.ProductResponse;
import javaproject.demo.mapper.ProductMapper;
import javaproject.demo.repository.ProductRepository;
import javaproject.demo.service.product.interfaces.IProductService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public List<ProductResponse> getAll() {
        var products = productRepository.findAll();
        return products.stream().map(ProductResponse::mapToProductResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsBySupplierId(Long id) {
        var products = productRepository.findProductsBySupplier_Id(id);
        return products.stream().map(ProductResponse::mapToProductResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getById(Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        return ProductResponse.mapToProductResponse(product);
    }

    @Override
    public ProductResponse create(ProductRequest product) {
        var newproduct = productMapper.fromRequest(product);
        return ProductResponse.mapToProductResponse(productRepository.save(newproduct));
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Category with this ID: " + id));
        product.setDescription(request.getDescription());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCountOfAvailability(request.getCountOfAvailability());
        product.setSupplier(request.getSupplier());
        return ProductResponse.mapToProductResponse(productRepository.save(product));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
