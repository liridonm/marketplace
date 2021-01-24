package com.patela.marketplace.controller;

import com.patela.marketplace.annotations.DefaultExceptionMessage;
import com.patela.marketplace.domain.common.ResponseWrapper;
import com.patela.marketplace.domain.dtos.CategoryDTO;
import com.patela.marketplace.exception.ServiceException;
import com.patela.marketplace.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ServerCloneException;
import java.util.List;

@RestController
@Tag(name = "Category Controller", description = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/api/v1/category")
    @DefaultExceptionMessage(defaultMessage = "Failed to retrieve categories")
    @Operation(summary = "Reads all categories")
    public ResponseEntity<ResponseWrapper> readAll() throws ServiceException{
        List<CategoryDTO> categories = categoryService.readAll();
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved all categories", categories));
    }

    @GetMapping(value = "/api/v1/category{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to read Category by id")
    @Operation(summary = "Read a category by ID")
    public ResponseEntity<ResponseWrapper> readById(@PathVariable("id") Integer id) throws ServiceException{
        CategoryDTO category = categoryService.readBy(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully retrieved the Category", category));
    }

    @PostMapping(value = "/api/v1/category")
    @DefaultExceptionMessage(defaultMessage = "Failed to create a Category")
    @Operation(summary = "Creates a Category")
    public ResponseEntity<ResponseWrapper> create(@RequestBody CategoryDTO category) throws ServiceException{
        CategoryDTO createCategory = categoryService.create(category);
        return ResponseEntity.ok(new ResponseWrapper("Successfully created a category", createCategory));
    }

    @PutMapping(value = "/api/v1/category")
    @DefaultExceptionMessage(defaultMessage = "FAiled to update the Category")
    @Operation(summary = "Updates a category")
    public ResponseEntity<ResponseWrapper> update(@RequestBody CategoryDTO category) throws ServiceException{
        CategoryDTO updateCategory = categoryService.create(category);
        return ResponseEntity.ok(new ResponseWrapper("Successfully created a category", updateCategory));
    }

    @DeleteMapping(value = "/api/v1/category{id}")
    @DefaultExceptionMessage(defaultMessage = "Failed to delete the Category")
    @Operation(summary = "Deletes a category matching the id")
    public ResponseEntity<ResponseWrapper> deleteById(@PathVariable("id") Integer id) throws ServiceException{
        categoryService.deleteById(id);
        return ResponseEntity.ok(new ResponseWrapper("Successfully deleted the Category with ID: " + id));
    }
}
