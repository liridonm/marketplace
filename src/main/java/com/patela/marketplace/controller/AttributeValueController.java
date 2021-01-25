package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.AttributeValueDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.AttributeValueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attribute_value")
@Tag(name = "Attribute Value Controller", description = "Attribute Value API")
public class AttributeValueController {

    private final AttributeValueService attributeValueService;

    public AttributeValueController(AttributeValueService attributeValueService) {
        this.attributeValueService = attributeValueService;
    }

    @GetMapping
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve product attribute values!")
    @Operation(summary = "Read all product attribute values")
    public ResponseEntity<ResponseWrapper> readAll() {
        List<AttributeValueDTO> attributeValues=  attributeValueService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved list of attribute values!", attributeValues));
    }

    @GetMapping("/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to read by id!")
    @Operation(summary = "Read attribute value by id")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Integer id) throws ServiceException {
        AttributeValueDTO attributeValue = attributeValueService.readById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved attribute value!", attributeValue));
    }

    @PostMapping
    @DefaultExceptionMessage(defaultMessage = "Failed to create attribute value!")
    @Operation(summary = "Create attribute value")
    public ResponseEntity<ResponseWrapper> create(@RequestBody AttributeValueDTO attributeValue) throws ServiceException {
        AttributeValueDTO c = attributeValueService.create(attributeValue);
        return ResponseEntity.ok(new ResponseWrapper("Attribute has been created!", attributeValue));
    }

    @PutMapping
    @DefaultExceptionMessage(defaultMessage = "Failed to update attribute value!")
    @Operation(summary = "Update attribute value")
    public ResponseEntity<ResponseWrapper> update(@RequestBody AttributeValueDTO attributeValue) throws ServiceException {
        AttributeValueDTO updatedAttribute = attributeValueService.update(attributeValue);
        return ResponseEntity.ok(new ResponseWrapper("Attribute value has been updated!", updatedAttribute));
    }

    @DeleteMapping("/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete attribute value!")
    @Operation(summary = "Delete attribute value by id")
    public ResponseEntity<ResponseWrapper> deleteById(@PathVariable("id") Integer id) throws ServiceException {
        attributeValueService.deleteById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully delete the attribute value!"));
    }
}
