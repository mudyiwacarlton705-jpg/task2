package pl.edu.vistula.task2.product.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.vistula.task2.product.api.request.ProductRequest;
import pl.edu.vistula.task2.product.api.request.UpdateProductRequest;
import pl.edu.vistula.task2.product.api.response.ProductResponse;
import pl.edu.vistula.task2.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create Product")
    public ResponseEntity<ProductResponse> create(
            @RequestBody ProductRequest request
    ) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    @Operation(summary = "Get All Products")
    public ResponseEntity<List<ProductResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Product By Id")
    public ResponseEntity<ProductResponse> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Product By Id")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Product")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}