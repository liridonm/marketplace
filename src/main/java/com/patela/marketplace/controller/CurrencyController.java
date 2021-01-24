package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.CurrencyDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.CurrencyService;
import com.patela.marketplace.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Currency Controller", description = "Currency API")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = {"/api/v1/currency", "/api/p1/currency"})
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve currencies!")
    @Operation(summary = "Read all currencies")
    public ResponseEntity<ResponseWrapper> readAll() {
        List<CurrencyDTO> currencies = currencyService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved list of currencies!", currencies));
    }

    @GetMapping("/api/v1/currency/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to read by id!")
    @Operation(summary = "Read currency by id")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Integer id) throws ServiceException {
        CurrencyDTO currency = currencyService.readById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved currency!", currency));
    }

    @PostMapping("/api/v1/currency")
    @DefaultExceptionMessage(defaultMessage = "Failed to create currency!")
    @Operation(summary = "Create currency")
    public ResponseEntity<ResponseWrapper> create(@RequestBody CurrencyDTO currency) throws ServiceException {
        CurrencyDTO createdCurrency = currencyService.create(currency);
        return ResponseEntity.ok(new ResponseWrapper("Currency has been created!", createdCurrency));
    }

    @PutMapping("/api/v1/currency")
    @DefaultExceptionMessage(defaultMessage = "Failed to update currency!")
    @Operation(summary = "Update currency")
    public ResponseEntity<ResponseWrapper> update(@RequestBody CurrencyDTO currency) throws ServiceException {
        CurrencyDTO updatedCurrency = currencyService.update(currency);
        return ResponseEntity.ok(new ResponseWrapper("Currency has been updated!", updatedCurrency));
    }

    @DeleteMapping("/api/v1/currency/{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete currency!")
    @Operation(summary = "Delete currency by id")
    public ResponseEntity<ResponseWrapper> deleteById(@PathVariable("id") Integer id) throws ServiceException {
        currencyService.deleteById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully delete the currency!"));
    }
}
