package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.RequestOrderDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.RequestOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@Tag(name = "Order Controller", description = "Order API")
public class RequestOrderController {

    private final RequestOrderService requestOrderService;

    public RequestOrderController(RequestOrderService requestOrderService) {
        this.requestOrderService = requestOrderService;
    }

    @PostMapping
    @DefaultExceptionMessage(defaultMessage = "Something went wrong please try again!")
    @Operation(summary = "Create order")
    public ResponseEntity<ResponseWrapper> create(@RequestBody RequestOrderDTO requestOrderDTO) throws ServiceException {
        RequestOrderDTO createdOrder = requestOrderService.create(requestOrderDTO);
        return ResponseEntity.ok(new ResponseWrapper("Product has been added to cart!", createdOrder));

    }

    @PutMapping("/confirm")
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, please try again")
    @Operation(summary = "Confirm an order")
    public ResponseEntity<ResponseWrapper> confirm(@RequestBody RequestOrderDTO requestOrderDTO) {
        RequestOrderDTO confirmedOrder = requestOrderService.confirmOrder(requestOrderDTO);
        return ResponseEntity.ok(new ResponseWrapper("Order has been confirmed", confirmedOrder));
    }

    @PutMapping
    @DefaultExceptionMessage(defaultMessage = "Something went wrong, please try again")
    @Operation(summary = "Update an order")
    public ResponseEntity<ResponseWrapper> update(@RequestBody RequestOrderDTO requestOrderDTO) throws ServiceException {
        RequestOrderDTO updateOrder = requestOrderService.update(requestOrderDTO);
        return ResponseEntity.ok(new ResponseWrapper("Order has been updated", updateOrder));
    }
}
