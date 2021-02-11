package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.RequestOrderLineDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.RequestOrderLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order-line")
@Tag(name = "Order Line Controller", description = "Order Line API")
public class RequestOrderLineController {


    private final RequestOrderLineService requestOrderLineService;

    public RequestOrderLineController(RequestOrderLineService requestOrderLineService) {
        this.requestOrderLineService = requestOrderLineService;
    }

    @PutMapping
    @DefaultExceptionMessage(defaultMessage = "Failed to update quantity of product!")
    @Operation(summary = "Update quantity")
    public ResponseEntity<ResponseWrapper> updateQuantity(@RequestBody RequestOrderLineDTO requestOrderLineDTO) throws ServiceException {
        RequestOrderLineDTO createOrderLine = requestOrderLineService.update(requestOrderLineDTO);
        return ResponseEntity.ok(new ResponseWrapper("Product has been added to cart!", createOrderLine));
    }
}
