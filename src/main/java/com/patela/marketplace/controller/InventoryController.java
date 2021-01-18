package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.InventoryDTO;
import com.patela.marketplace.domain.dtos.ProductDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Inventory Controller", description = "Inventory API")
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PutMapping
    @DefaultExceptionMessage(defaultMessage = "Failed to add quantity!")
    @Operation(summary = "Add quantity on stock")
    public ResponseEntity<ResponseWrapper> update(@RequestBody InventoryDTO inventoryDTO) throws ServiceException {
        InventoryDTO inventory = inventoryService.addInventoryOnStock(inventoryDTO);
        return ResponseEntity.ok(new ResponseWrapper("Quantity has been added!", inventory));
    }

}
