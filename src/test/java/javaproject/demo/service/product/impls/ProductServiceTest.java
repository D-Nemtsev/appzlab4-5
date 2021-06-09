package javaproject.demo.service.product.impls;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javaproject.demo.entities.Product;
import javaproject.demo.mapper.ProductMapper;
import javaproject.demo.repository.ProductRepository;
import javaproject.demo.stubs.OrderStub;
import javaproject.demo.stubs.ProductStub;
import javaproject.demo.stubs.SupplierStub;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private ProductService service;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    void setup() {
        service = new ProductService(productRepository, productMapper);
    }

    @Test
    void getSuccessful() {
        List<Product> list = new ArrayList<Product>();
        var product = ProductStub.getRandomProduct();
        list.add(product);
        list.add(product);
        list.add(product);

        when(productRepository.findAll()).thenReturn(list);
        var getAll = service.getAll();
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulByTypeId() {
        List<Product> list = new ArrayList<Product>();
        var product = ProductStub.getRandomProduct();
        list.add(product);
        list.add(product);
        list.add(product);

        when(productRepository.findProductsBySupplier_Id(SupplierStub.ID)).thenReturn(list);
        var getAll = service.getProductsBySupplierId(SupplierStub.ID);
        assertEquals(list.size(), getAll.size());
    }

    @Test
    void getSuccessfulById() {
        var product = ProductStub.getRandomProduct();
        when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(product));
        var byId = service.getById(ProductStub.ID);

        assertAll(() -> assertEquals(byId.getId(), product.getId()),
                () -> assertEquals(byId.getName(), product.getName()),
                () -> assertEquals(byId.getDescription(), product.getDescription()),
                () -> assertEquals(byId.getPrice(), product.getPrice()),
                () -> assertEquals(byId.getCountOfAvailability(), product.getCountOfAvailability()),
                () -> assertEquals(byId.getSupplier().getId(), product.getSupplier().getId()) );
    }


    @Test
    void createSuccessful() {
        var captor = ArgumentCaptor.forClass(Product.class);
        var product = ProductStub.getRandomProduct();
        when(productMapper.fromRequest(Mockito.any())).thenReturn(product);
        when(productRepository.save(Mockito.any())).thenReturn(ProductStub.getRandomProduct());
        var result = service.create(ProductStub.getProductRequest());
        Mockito.verify(productRepository, atLeast(1)).save(captor.capture());
        assertEquals(product, captor.getValue());
        assertEquals(product.getName(), result.getName());
    }

    @Test
    void updateSuccessful() {
        var captor = ArgumentCaptor.forClass(Product.class);
        var product = ProductStub.getRandomProduct();
        when(productRepository.findById(Mockito.any())).thenReturn(Optional.of(ProductStub.getRandomProduct()));
        when(productRepository.save(Mockito.any())).thenReturn(ProductStub.getRandomProduct());
        var result = service.update(ProductStub.ID, ProductStub.getProductRequest());
        Mockito.verify(productRepository, atLeast(1)).save(captor.capture());
        assertEquals(product, captor.getValue());
        assertEquals(product.getName(), result.getName());
    }

    @Test
    void deleteSuccessful() {
        service.delete(ProductStub.ID);
        var captor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(productRepository, atLeast(1)).deleteById(captor.capture());
        assertEquals(ProductStub.ID, captor.getValue());
    }

}