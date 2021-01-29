package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.ProductDTO;
import com.patela.marketplace.domain.entities.Product;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Product Controller", description = "Product API")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/api/v1/product", "/api/p1/product"})
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve products!")
    @Operation(summary = "Read all products")
    public ResponseEntity<ResponseWrapper> readAll() {
        List<ProductDTO> products = productService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved list of products!", products));

    }

    @GetMapping(value = {"/api/v1/product/{id}", "/api/p1/product/{id}"})
    @DefaultExceptionMessage(defaultMessage = "Failed to read by id!")
    @Operation(summary = "Read product by id")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Integer id) throws ServiceException {
        ProductDTO product = productService.readById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved product!", product));
    }

    @PostMapping("/api/v1/product")
    @DefaultExceptionMessage(defaultMessage = "Failed to create product!")
    @Operation(summary = "Create product")
    public ResponseEntity<ResponseWrapper> create(@RequestBody ProductDTO productDTO) throws ServiceException {
        ProductDTO createdProduct = productService.create(productDTO);
        return ResponseEntity.ok(new ResponseWrapper("Product has been created!", createdProduct));
    }

    @PutMapping("/api/v1/product")
    @DefaultExceptionMessage(defaultMessage = "Failed to update product!")
    @Operation(summary = "Update product")
    public ResponseEntity<ResponseWrapper> update(@RequestBody ProductDTO productDTO) throws ServiceException {
        ProductDTO updatedProduct = productService.update(productDTO);
        return ResponseEntity.ok(new ResponseWrapper("Product has been updated!", updatedProduct));
    }

    @PutMapping("/api/v1/product/suspend/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to suspend product!")
    @Operation(summary = "Suspend product")
    public ResponseEntity<ResponseWrapper> suspendProduct(@PathVariable("id") Integer id) throws ServiceException {
        ProductDTO suspendProduct = productService.suspendProduct(id);
        return ResponseEntity.ok(new ResponseWrapper("Product has been suspended!", suspendProduct));
    }
}
