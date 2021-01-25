package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.AttributeDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attribute")
@Tag(name = "Attribute Controller", description = "Attribute API")
public class AttributeController {

    private final AttributeService attributeService;

    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @GetMapping
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve product attributes!")
    @Operation(summary = "Read all product attributes")
    public ResponseEntity<ResponseWrapper> readAll() {
        List<AttributeDTO> attributes = attributeService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved list of attributes!", attributes));
    }

    @GetMapping("/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to read by id!")
    @Operation(summary = "Read attribute by id")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Integer id) throws ServiceException {
        AttributeDTO attribute = attributeService.readById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved attribute!", attribute));
    }

    @PostMapping
    @DefaultExceptionMessage(defaultMessage = "Failed to create attribute!")
    @Operation(summary = "Create attribute")
    public ResponseEntity<ResponseWrapper> create(@RequestBody AttributeDTO attribute) throws ServiceException {
        AttributeDTO createdAttribute = attributeService.create(attribute);
        return ResponseEntity.ok(new ResponseWrapper("Attribute has been created!", createdAttribute));
    }

    @PutMapping
    @DefaultExceptionMessage(defaultMessage = "Failed to update attribute!")
    @Operation(summary = "Update attribute")
    public ResponseEntity<ResponseWrapper> update(@RequestBody AttributeDTO attribute) throws ServiceException {
        AttributeDTO updatedAttribute = attributeService.update(attribute);
        return ResponseEntity.ok(new ResponseWrapper("Attribute has been updated!", updatedAttribute));
    }

    @DeleteMapping("/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete attribute!")
    @Operation(summary = "Delete attribute by id")
    public ResponseEntity<ResponseWrapper> deleteById(@PathVariable("id") Integer id) throws ServiceException {
        attributeService.deleteById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully delete the attribute!"));
    }
}
