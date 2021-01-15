package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.UomDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.UomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "UOM Controller", description = "Uom API")
public class UomController {

    private final UomService uomService;

    public UomController(UomService uomService) {
        this.uomService = uomService;
    }

    @GetMapping(value = {"/api/v1/uom", "/api/p1/uom"})
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve unit of measures!")
    @Operation(summary = "Read all UOM-s")
    public ResponseEntity<ResponseWrapper> readAll() {
        List<UomDTO> uomList = uomService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved list of uom-s!", uomList));
    }

    @GetMapping("/api/v1/uom/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to read by id!")
    @Operation(summary = "Read UOM by id")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Integer id) throws ServiceException {
        UomDTO uom = uomService.readById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved uom!", uom));
    }

    @PostMapping("/api/v1/uom")
    @DefaultExceptionMessage(defaultMessage = "Failed to create uom!")
    @Operation(summary = "Create uom")
    public ResponseEntity<ResponseWrapper> create(@RequestBody UomDTO uom) throws ServiceException {
        UomDTO createdUom = uomService.create(uom);
        return ResponseEntity.ok(new ResponseWrapper("Uom has been created!", createdUom));
    }

    @PutMapping("/api/v1/uom")
    @DefaultExceptionMessage(defaultMessage = "Failed to update uom!")
    @Operation(summary = "Update uom")
    public ResponseEntity<ResponseWrapper> update(@RequestBody UomDTO uom) throws ServiceException {
        UomDTO updateUom = uomService.update(uom);
        return ResponseEntity.ok(new ResponseWrapper("Uom has been updated!", updateUom));
    }

    @DeleteMapping("/api/v1/uom/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete uom!")
    @Operation(summary = "Delete UOM by id")
    public ResponseEntity<ResponseWrapper> deleteById(@PathVariable("id") Integer id) throws ServiceException {
        uomService.deleteById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully delete the uom!"));
    }


}
