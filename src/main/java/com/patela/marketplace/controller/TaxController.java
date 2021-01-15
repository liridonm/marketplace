package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.TaxDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.TaxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Tax Controller" , description = "Tax API")
public class TaxController {
    private final TaxService taxService;

    public TaxController(TaxService taxService){
        this.taxService = taxService;
    }

    @GetMapping("/api/v1/tax")
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve the list of taxes")
    @Operation(summary = "Reads all the taxes")
    public ResponseEntity<ResponseWrapper> readAll() throws ServiceException{
        List<TaxDTO> taxes = taxService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved all the taxes", taxes));
    }

    @GetMapping("/api/v1/tax{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve the Tax")
    @Operation(summary = "Retrieves a tax matching the ID")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Integer id) throws ServiceException{
        TaxDTO tax = taxService.readBy(id);
        return  ResponseEntity.ok(new ResponseWrapper("Successfully retrieved the tax with ID: " + id, tax));
    }

    @PostMapping("/api/v1/tax")
    @DefaultExceptionMessage(defaultMessage = "Failed to create Tax")
    @Operation(summary = "Creates a Tax")
    public ResponseEntity<ResponseWrapper> create(@RequestBody TaxDTO tax) throws ServiceException{
        TaxDTO createdTax = taxService.create(tax);
        return ResponseEntity.ok(new ResponseWrapper("Successfully created a Tax", createdTax));
    }

    @PutMapping("/api/v1/tax")
    @DefaultExceptionMessage(defaultMessage = "Failed to update the Tax")
    @Operation(summary = "Updates a Tax")
    public ResponseEntity<ResponseWrapper> update(@RequestBody TaxDTO tax) throws ServiceException{
        TaxDTO updateTax = taxService.create(tax);
        return ResponseEntity.ok(new ResponseWrapper("Successfully created a Tax", updateTax));
    }

    @DeleteMapping("/api/v1/tax{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete the Tax")
    @Operation(summary = "Deletes a TAx")
    public ResponseEntity<ResponseWrapper> deleteById(@PathVariable("id") Integer id) throws ServiceException{
        taxService.deleteById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully deleted the tax with ID: " + id));
    }
}
