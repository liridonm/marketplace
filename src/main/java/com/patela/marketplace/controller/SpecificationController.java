package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.SpecificationDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.SpecificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Specification Controller", description = "Specification API")
public class SpecificationController {
    private final SpecificationService specificationService;

    public SpecificationController(SpecificationService specificationService){
        this.specificationService = specificationService;
    }

    @GetMapping("/api/v1/specification")
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve the list of specifications")
    @Operation(summary = "Reads all specifications")
    public ResponseEntity<ResponseWrapper> readAll() throws ServiceException {
        List<SpecificationDTO> specificationList = specificationService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved specifications", specificationList));
    }

    @GetMapping("/api/v1/specification{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve the specification")
    @Operation(summary = "Reads a Specification mathing the id")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Integer id) throws ServiceException{
        SpecificationDTO specification = specificationService.readById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved the Specification with ID: " + id, specification));
    }

    @PostMapping("/api/v1/specification")
    @DefaultExceptionMessage(defaultMessage = "Failed to create a Specification")
    @Operation(summary = "Creates a Specification")
    public ResponseEntity<ResponseWrapper> create(@RequestBody SpecificationDTO specification) throws ServiceException{
        SpecificationDTO createdSpecification = specificationService.create(specification);
        return ResponseEntity.ok(new ResponseWrapper("Successfully created a Specification", createdSpecification));
    }
    
    @PutMapping("/api/v1/specification")
    @DefaultExceptionMessage(defaultMessage = "Failed to update the Specification")
    @Operation(summary = "Updates a Specification matching an ID")
    public ResponseEntity<ResponseWrapper> update(@RequestBody SpecificationDTO specification) throws ServiceException{
        SpecificationDTO updatedSpecification = specificationService.create(specification);
        return ResponseEntity.ok(new ResponseWrapper("Successfully created a Specification", updatedSpecification));
    }

    @DeleteMapping("/api/v1/specification{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete the Specification")
    @Operation(summary = "Deletes a Specification matching the ID")
    public ResponseEntity<ResponseWrapper> deleteById(Integer id) throws ServiceException{
        specificationService.deleteById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully deleted the Specification with ID: " + id));
    }


}
