package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.BrandDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Brand Controller" , description = "Brand API")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {this.brandService = brandService; }

    @GetMapping(value = {"/api/v1/brand", "/api/p1/brand"})
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve brands")
    @Operation(summary = "Read all brands")
    public ResponseEntity<ResponseWrapper> readAll() throws  ServiceException{
        List<BrandDTO> brandList = brandService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved all brands!", brandList));
    }

    @GetMapping(value = {"/api/v1/brand{id}"})
    @DefaultExceptionMessage(defaultMessage = "Failed to read brand by id")
    @Operation(summary = "Read a brand by ID")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Integer id) throws ServiceException{
        BrandDTO brand = brandService.readById(id);
        return  ResponseEntity.ok(new ResponseWrapper("Successfully retrieved the Brand with ID: " + id, brand));
    }

    @PostMapping(value = {"/api/v1/brand"})
    @DefaultExceptionMessage(defaultMessage = "Failed to create brand")
    @Operation(summary = "Create a Brand")
    public ResponseEntity<ResponseWrapper> create(@RequestBody BrandDTO brand) throws  ServiceException{
        BrandDTO createBrand = brandService.create(brand);
        return ResponseEntity.ok(new ResponseWrapper("Brand has been created", createBrand));
    }

    @PutMapping(value = "/api/v1/brand")
    @DefaultExceptionMessage(defaultMessage = "Failed to update Brand")
    @Operation(summary = "Update a Brand")
    public ResponseEntity<ResponseWrapper> update(@RequestBody BrandDTO brand) throws  ServiceException{
        BrandDTO updateBrand = brandService.create(brand);
        return ResponseEntity.ok(new ResponseWrapper("Brand has been created", updateBrand));
    }

    @DeleteMapping(value = "/api/v1/brand{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete Brand")
    @Operation(summary = "Delete a Brand matching an ID")
    public ResponseEntity<ResponseWrapper> deleteById(@PathVariable("id") Integer id) throws  ServiceException{
        brandService.deleteById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully deleted the Brand with ID: " + id));
    }
}
