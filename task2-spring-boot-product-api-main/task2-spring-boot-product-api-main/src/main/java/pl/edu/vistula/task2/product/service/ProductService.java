package pl.edu.vistula.task2.product.service;

import org.springframework.stereotype.Service;
import pl.edu.vistula.task2.product.api.request.ProductRequest;
import pl.edu.vistula.task2.product.api.request.UpdateProductRequest;
import pl.edu.vistula.task2.product.api.response.ProductResponse;
import pl.edu.vistula.task2.product.domain.Product;
import pl.edu.vistula.task2.product.repository.ProductRepository;
import pl.edu.vistula.task2.product.support.ProductMapper;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProductResponse create(ProductRequest request) {
        Product product = mapper.toProduct(request);
        Product saved = repository.save(product);
        return mapper.toProductResponse(saved);
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    public ProductResponse findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return mapper.toProductResponse(product);
    }

    public ProductResponse update(Long id, UpdateProductRequest request) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());

        Product saved = repository.save(product);
        return mapper.toProductResponse(saved);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}