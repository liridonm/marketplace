package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.CategoryTypeDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.CategoryTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Category Type Controller", description = "Category Type API")
public class CategoryTypeController {
    private final CategoryTypeService categoryTypeService;

    public CategoryTypeController(CategoryTypeService categoryTypeService){
        this.categoryTypeService = categoryTypeService;
    }

    @GetMapping("/api/v1/category-type")
    @DefaultExceptionMessage(defaultMessage = "Failed to read the list of Category Types")
    @Operation(summary = "Reads all Category Types")
    public ResponseEntity<ResponseWrapper> readAll() throws ServiceException{
        List<CategoryTypeDTO> categoryTypes = categoryTypeService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved all Category Types", categoryTypes));
    }

    @GetMapping("/api/v1/category-type{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve the Category Type for the given ID")
    @Operation(summary = "Reads a category type for a matching id")
    public ResponseEntity<ResponseWrapper> readyById(@PathVariable("id") Integer id) throws ServiceException{
        CategoryTypeDTO categoryType = categoryTypeService.readBy(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved the Category Type", categoryType));
    }

    @PostMapping("/api/v1/category-type")
    @DefaultExceptionMessage(defaultMessage = "Failed to create a Category Type")
    @Operation(summary = "Creates a category type")
    public ResponseEntity<ResponseWrapper> create(@RequestBody CategoryTypeDTO categoryType) throws ServiceException{
        CategoryTypeDTO createdCategoryType = categoryTypeService.create(categoryType);
        return ResponseEntity.ok(new ResponseWrapper("Successfully created a Category Type", createdCategoryType));
    }

    @PutMapping("/api/v1/category-type")
    @DefaultExceptionMessage(defaultMessage = "Failed to update a Category Type")
    @Operation(summary = "Updates a Category Type")
    public ResponseEntity<ResponseWrapper> update(@RequestBody CategoryTypeDTO categoryType) throws ServiceException{
        CategoryTypeDTO updatedCategoryType = categoryTypeService.create(categoryType);
        return ResponseEntity.ok(new ResponseWrapper("Successfully created a Category Type", updatedCategoryType));
    }

    @DeleteMapping("/api/v1/category-type{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete the Category Type")
    @Operation(summary = "Deletes a Category Type matching the id")
    public ResponseEntity<ResponseWrapper> deleteById(@PathVariable("id") Integer id) throws ServiceException{
        categoryTypeService.deleteById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully deleted the Category Type with ID: " + id));
    }
}
